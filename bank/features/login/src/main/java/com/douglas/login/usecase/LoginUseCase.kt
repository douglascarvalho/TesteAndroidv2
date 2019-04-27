package com.douglas.login.usecase

import com.douglas.login.data.LastLoggedUser
import com.douglas.login.data.source.LoginRepository
import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse

class LoginUseCase(
    private val loginRepository: LoginRepository
) {

    suspend fun doLogin(loginRequest: LoginRequest): LoginResponse? {
        return loginRepository.doLogin(loginRequest)
    }

    suspend fun saveLastLoggedUser(lastLoggedUser: LastLoggedUser) {
        loginRepository.saveLastLoggedUser(lastLoggedUser)
    }

    suspend fun getLastLoggedUser(): LastLoggedUser?{
        return loginRepository.getLastLoggedUser()
    }
}