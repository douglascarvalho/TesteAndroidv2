package com.douglas.login.data.source

import com.douglas.login.data.LastLoggedUser
import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse

interface LoginDataSource {

    suspend fun doLogin(loginRequest: LoginRequest): LoginResponse?

    suspend fun saveLastLoggedUser(lastLoggedUser: LastLoggedUser)
    suspend fun getLastLoggedUser() : LastLoggedUser?

}