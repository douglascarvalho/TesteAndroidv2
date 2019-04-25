package com.douglas.login

import com.douglas.login.model.UserAccount

sealed class LoginViewState {
    data class Success(val userAccount: UserAccount) : LoginViewState()
    object Error : LoginViewState()
}