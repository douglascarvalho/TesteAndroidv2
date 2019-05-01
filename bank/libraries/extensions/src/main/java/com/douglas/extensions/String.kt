package com.douglas.extensions

import java.lang.StringBuilder

fun String.toBankAccountFormat(): String {
    return StringBuilder(this).insert(2, '.').insert(this.length, '-').toString()
}