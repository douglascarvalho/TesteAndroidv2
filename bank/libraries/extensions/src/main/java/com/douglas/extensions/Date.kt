package com.douglas.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toBrazilianFormat() : String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
    return try {
        dateFormat.format(this)
    } catch (e: RuntimeException) {
        this.toString()
    }

}