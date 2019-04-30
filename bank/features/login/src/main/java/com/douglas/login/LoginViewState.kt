package com.douglas.login

import com.douglas.actions.extras.Account

sealed class LoginViewState {
    data class Success(val account: Account) : LoginViewState()
    data class Error(val error: com.douglas.login.model.Error) : LoginViewState()
    object NetworkError : LoginViewState()

    data class SuggestLastLoggedUser(val user: String) : LoginViewState()

    object InvalidUsername : LoginViewState()
    object WeakPassword : LoginViewState()
}