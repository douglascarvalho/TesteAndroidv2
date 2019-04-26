package com.douglas.login.usecase

import com.douglas.login.data.LoginApi
import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val loginApi: LoginApi
) {

    suspend fun execute(loginRequest: LoginRequest): LoginResponse? = withContext(Dispatchers.IO) {
        val response = loginApi.login(loginRequest).await()
        return@withContext if (response.isSuccessful) response.body() else null
    }

}