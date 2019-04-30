package com.douglas.login.data.source

import com.douglas.login.data.LastLoggedUser
import com.douglas.login.data.LoginRequest
import com.douglas.login.data.LoginResponse

interface LoginDataSource {

    suspend fun doLogin(loginRequest: LoginRequest): LoginResponse?

    suspend fun saveLastLoggedUser(lastLoggedUser: LastLoggedUser)
    suspend fun getLastLoggedUser() : LastLoggedUser?

}