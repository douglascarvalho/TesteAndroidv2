package com.douglas.actions.extras

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    val id: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
) : Parcelable {
    companion object {
        const val EXTRA_KEY = "account"
    }
}