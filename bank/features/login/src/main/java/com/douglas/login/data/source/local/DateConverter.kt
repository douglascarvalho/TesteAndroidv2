package com.douglas.login.data.source.local

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(timeInMillis: Long?) : Date? = timeInMillis?.let { Date(timeInMillis) }

    @TypeConverter
    fun fromDate(date: Date?) : Long? = date?.time

}