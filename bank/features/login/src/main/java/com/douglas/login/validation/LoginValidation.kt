package com.douglas.login.validation

import android.util.Patterns

object LoginValidation {

    fun isWeakPassword(password: String) : Boolean {
        return (
                password.contains("(?=.*?[#?!@\$%^&*-])".toRegex()) &&
                        password.contains("(.*\\d.*)".toRegex()) &&
                        password.contains("(.*[A-Z].*)".toRegex())
                ).not()
    }

    fun isInvalidUsername(username: String) : Boolean {

        fun isEmail() =
            username.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})${"+"}".toRegex())

        fun isCPF(): Boolean {
            if (username.isEmpty()) return false

            val numbers = arrayListOf<Int>()

            username.filter { it.isDigit() }.forEach {
                numbers.add(it.toString().toInt())
            }

            if (numbers.size != 11) return false

            (0..9).forEach { n ->
                val digits = arrayListOf<Int>()
                (0..10).forEach { digits.add(n) }
                if (numbers == digits) return false
            }

            val dv1 = ((0..8).sumBy { (it + 1) * numbers[it] }).rem(11).let {
                if (it >= 10) 0 else it
            }

            val dv2 = ((0..8).sumBy { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
                if (it >= 10) 0 else it
            }

            return numbers[9] == dv1 && numbers[10] == dv2
        }

        return (isEmail() || isCPF()).not()
    }


 }