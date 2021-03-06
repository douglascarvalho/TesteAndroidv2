package com.douglas.login.data.source.remote

import com.douglas.login.data.LastLoggedUser
import com.douglas.login.data.LoginApi
import com.douglas.login.data.source.LoginDataSource
import com.douglas.login.data.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRemoteDataSource(
    private val loginApi: LoginApi
) : LoginDataSource {

    override suspend fun doLogin(loginRequest: LoginRequest) = withContext(Dispatchers.IO){
        val response = loginApi.login(loginRequest).await()
        return@withContext if (response.isSuccessful) response.body() else null
    }

    override suspend fun getLastLoggedUser(): LastLoggedUser? = null
    override suspend fun saveLastLoggedUser(lastLoggedUser: LastLoggedUser) {}
}