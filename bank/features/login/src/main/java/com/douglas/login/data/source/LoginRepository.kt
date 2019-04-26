package com.douglas.login.data.source

import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse

class LoginRepository (
    private val loginLocalDataSource: LoginDataSource,
    private val loginRemoteDataSource: LoginDataSource
) : LoginDataSource {

    override suspend fun doLogin(loginRequest: LoginRequest): LoginResponse? {
        return loginRemoteDataSource.doLogin(loginRequest)
    }

}