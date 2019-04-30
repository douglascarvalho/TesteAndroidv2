package com.douglas.actions.extras

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    val id: String,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: String
) : Parcelable {
    companion object {
        const val EXTRA_KEY = "account"
    }
}