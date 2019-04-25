package com.douglas.login

import com.douglas.login.data.LoginApi
import com.douglas.login.model.Login
import com.douglas.login.model.UserAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val loginApi: LoginApi
) {

    suspend fun execute(login: Login): UserAccount? = withContext(Dispatchers.IO) {
        val response = loginApi.login(login).await()
        return@withContext if (response.isSuccessful) response.body() else null
    }

}