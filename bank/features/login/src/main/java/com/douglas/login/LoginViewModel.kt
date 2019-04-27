package com.douglas.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.douglas.core.BaseViewModel
import com.douglas.login.data.LastLoggedUser
import com.douglas.login.model.Error
import com.douglas.login.model.LoginRequest
import com.douglas.login.usecase.LoginUseCase
import com.douglas.login.validation.LoginValidation
import kotlinx.coroutines.launch
import java.util.*

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val state = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState> = state

    init {
        launch {
            loginUseCase.getLastLoggedUser()?.let {
                state.value = LoginViewState.SuggestLastLoggedUser(it.user)
            }
        }
    }
    fun authenticate(username: String, password: String) {
        if (isUsernameAndPasswordValid(username, password)) {
            signIn(LoginRequest(username, password))
        }
    }

    private fun isUsernameAndPasswordValid(username: String, password: String): Boolean {
        var loginValid = true

        if (LoginValidation.isInvalidUsername(username)) {
            loginValid = false
            state.value = LoginViewState.InvalidUsername
        }
        if (LoginValidation.isWeakPassword(password)) {
            loginValid = false
            state.value = LoginViewState.WeakPassword
        }

        return loginValid
    }

    private fun signIn(loginRequest: LoginRequest) {
        launch {
            try {
                val userAccount = loginUseCase.doLogin(loginRequest)

                userAccount?.let {
                    if (hasLoginFailed(it.error)) {
                        state.value = LoginViewState.Error(it.error)
                    } else {
                        state.value = LoginViewState.Success(it.userAccount)
                        loginUseCase.saveLastLoggedUser(LastLoggedUser(null, loginRequest.user, Date()))
                    }
                } ?: run {
                    dispatchNetworkError()
                }
            } catch (e: Exception) {
                dispatchNetworkError()
            }
        }
    }

    private fun hasLoginFailed(error: Error) = error.code != 0

    private fun dispatchNetworkError() {
        state.value = LoginViewState.NetworkError
    }

}