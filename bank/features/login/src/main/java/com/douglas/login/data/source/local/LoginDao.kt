package com.douglas.login.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.douglas.login.data.LastLoggedUser

@Dao interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveLastLoggedUser(lastLoggedUser: LastLoggedUser)

    @Query("SELECT * from LastLoggedUser ORDER BY insertDate DESC LIMIT 1") fun getLastLoggedUser(): LastLoggedUser?

}