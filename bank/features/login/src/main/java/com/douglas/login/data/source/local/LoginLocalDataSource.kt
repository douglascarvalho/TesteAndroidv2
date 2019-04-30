package com.douglas.login.data.source.local

import com.douglas.login.data.LastLoggedUser
import com.douglas.login.data.source.LoginDataSource
import com.douglas.login.data.LoginRequest
import com.douglas.login.data.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginLocalDataSource(
    private val loginDao: LoginDao
) : LoginDataSource {

    override suspend fun saveLastLoggedUser(lastLoggedUser: LastLoggedUser) = withContext(Dispatchers.IO)  {
        loginDao.saveLastLoggedUser(lastLoggedUser)
    }

    override suspend fun getLastLoggedUser(): LastLoggedUser? = withContext(Dispatchers.IO) {
        return@withContext loginDao.getLastLoggedUser()
    }

    override suspend fun doLogin(loginRequest: LoginRequest): LoginResponse? = null
}