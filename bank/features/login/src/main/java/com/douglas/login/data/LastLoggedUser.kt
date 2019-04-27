package com.douglas.login.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LastLoggedUser(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo val user: String,
    @ColumnInfo val insertDate: Date
)