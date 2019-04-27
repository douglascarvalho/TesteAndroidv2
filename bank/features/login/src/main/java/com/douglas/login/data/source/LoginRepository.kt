package com.douglas.login.data.source

import com.douglas.login.data.LastLoggedUser
import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse

class LoginRepository (
    private val loginLocalDataSource: LoginDataSource,
    private val loginRemoteDataSource: LoginDataSource
) : LoginDataSource {

    override suspend fun doLogin(loginRequest: LoginRequest): LoginResponse? {
        return loginRemoteDataSource.doLogin(loginRequest)
    }

    override suspend fun saveLastLoggedUser(lastLoggedUser: LastLoggedUser) {
        loginLocalDataSource.saveLastLoggedUser(lastLoggedUser)
    }

    override suspend fun getLastLoggedUser(): LastLoggedUser? {
        return loginLocalDataSource.getLastLoggedUser()
    }
}