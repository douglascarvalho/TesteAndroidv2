package com.douglas.login.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.douglas.login.data.LastLoggedUser

@Database(entities = [LastLoggedUser::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

}