package com.doug.statement.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Statement (
    @SerializedName("desc")
    val description: String,
    val title: String,
    val date: Date,
    val value: Double
)