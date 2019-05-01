package com.douglas.extensions

import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*

fun Double.toBrazilianCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return try {
        numberFormat.format(this)
    } catch (e: NumberFormatException) {
        this.toString()
    }
}