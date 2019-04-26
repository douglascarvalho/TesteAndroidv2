package com.douglas.login.data.source

import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse

interface LoginDataSource {

    suspend fun doLogin(loginRequest: LoginRequest): LoginResponse?

}