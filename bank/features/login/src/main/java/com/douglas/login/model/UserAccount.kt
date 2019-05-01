package com.douglas.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccount(
    val userId: Int,
    val name: String,

    @SerializedName("agency")
    val bankAccount: String,

    @SerializedName("bankAccount")

    val agency: String,
    val balance: Double
) : Parcelable