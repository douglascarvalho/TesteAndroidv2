package com.douglas.login.data.source.local

import com.douglas.login.data.source.LoginDataSource
import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse

class LoginLocalDataSource : LoginDataSource {

    override suspend fun doLogin(loginRequest: LoginRequest): LoginResponse? {
        return null
    }

}