package com.douglas.extensions

import android.app.Activity
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private val Activity.argumentsFinder: Activity.(String) -> Any?
    get() = { intent.extras?.get(it) }
private val Fragment.argumentsFinder: Fragment.(String) -> Any?
    get() = { arguments?.get(it) }

fun <T : Any> Activity.bindBundle(key: String, default: T? = null):
        ReadOnlyProperty<Activity, T> = if (default == null) {
    required(key, argumentsFinder)
} else {
    requiredDefault(key, default, argumentsFinder)
}

fun <T : Any> Fragment.bindBundle(key: String, default: T? = null):
        ReadOnlyProperty<Fragment, T> = if (default == null) {
    required(key, argumentsFinder)
} else {
    requiredDefault(key, default, argumentsFinder)
}

fun <T> Activity.bindOptionalBundle(key: String):
        ReadOnlyProperty<Activity, T?> = optional(key, argumentsFinder)

fun <T> Fragment.bindOptionalBundle(key: String):
        ReadOnlyProperty<Fragment, T?> = optional(key, argumentsFinder)

@Suppress("UNCHECKED_CAST")
private fun <T, B : Any?> required(key: String, finder: T.(String) -> Any?) = Lazy { t: T, desc ->
    t.finder(key) as B ?: bundleNotFound(key, desc)
}

@Suppress("UNCHECKED_CAST")
private fun <T, B : Any?> requiredDefault(key: String, default: B, finder: T.(String) -> Any?) = Lazy { t: T, _ ->
    t.finder(key) as B? ?: default
}

@Suppress("UNCHECKED_CAST")
private fun <T, B : Any?> optional(key: String, finder: T.(String) -> Any?) = Lazy { t: T, _ -> t.finder(key) as B? }

private fun bundleNotFound(key: String, desc: KProperty<*>): Nothing =
    throw IllegalStateException("Bundle KEY $key for '${desc.name}' not found.")

@Suppress("ComplexMethod")
fun bundleOf(vararg pairs: Pair<String, Any?>) = Bundle(pairs.size).apply {
    for ((key, value) in pairs) {
        when (value) {
            null -> putString(key, null)

            // Scalars
            is Boolean -> putBoolean(key, value)
            is Byte -> putByte(key, value)
            is Char -> putChar(key, value)
            is Double -> putDouble(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Short -> putShort(key, value)

            // Referências
            is Bundle -> putBundle(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Parcelable -> putParcelable(key, value)

            // Arrays escalares
            is BooleanArray -> putBooleanArray(key, value)
            is ByteArray -> putByteArray(key, value)
            is CharArray -> putCharArray(key, value)
            is DoubleArray -> putDoubleArray(key, value)
            is FloatArray -> putFloatArray(key, value)
            is IntArray -> putIntArray(key, value)
            is LongArray -> putLongArray(key, value)
            is ShortArray -> putShortArray(key, value)

            // Arrays de referência
            is Array<*> -> {
                val componentType = value::class.java.componentType
                @Suppress("UNCHECKED_CAST")
                when {
                    componentType == null -> throw IllegalArgumentException(
                        "ComponentType cannot be null")
                    Parcelable::class.java.isAssignableFrom(componentType) -> {
                        putParcelableArray(key, value as Array<Parcelable>)
                    }
                    String::class.java.isAssignableFrom(componentType) -> {
                        putStringArray(key, value as Array<String>)
                    }
                    CharSequence::class.java.isAssignableFrom(componentType) -> {
                        putCharSequenceArray(key, value as Array<CharSequence>)
                    }
                    Serializable::class.java.isAssignableFrom(componentType) -> {
                        putSerializable(key, value)
                    }
                    else -> {
                        val valueType = componentType.canonicalName
                        throw IllegalArgumentException(
                            "Valor inválido do array $valueType para chave \"$key\"")
                    }
                }
            }

            is Serializable -> putSerializable(key, value)

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && value is Binder) {
                    putBinder(key, value)
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && value is Size) {
                    putSize(key, value)
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && value is SizeF) {
                    putSizeF(key, value)
                } else {
                    val valueType = value.javaClass.canonicalName
                    throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
                }
            }
        }
    }
}