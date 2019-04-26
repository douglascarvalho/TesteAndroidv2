package com.douglas.login

import com.douglas.login.model.UserAccount

sealed class LoginViewState {
    data class Success(val userAccount: UserAccount) : LoginViewState()
    data class Error(val errorMessage: String) : LoginViewState()
    object NetworkError : LoginViewState()
}