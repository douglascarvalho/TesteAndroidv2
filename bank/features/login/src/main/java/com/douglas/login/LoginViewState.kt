package com.douglas.login

import com.douglas.login.model.UserAccount

sealed class LoginViewState {
    data class Success(val userAccount: UserAccount) : LoginViewState()
    data class Error(val error: com.douglas.login.model.Error) : LoginViewState()
    object NetworkError : LoginViewState()

    data class SuggestLastLoggedUser(val user: String) : LoginViewState()

    object InvalidUsername : LoginViewState()
    object WeakPassword : LoginViewState()
}