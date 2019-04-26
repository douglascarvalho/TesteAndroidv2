package com.douglas.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.douglas.core.BaseViewModel
import com.douglas.login.model.Error
import com.douglas.login.model.LoginRequest
import com.douglas.login.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val state = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState> = state

    fun authenticate(username: String, password: String) {
        signIn(
            LoginRequest(username, password)
        )
    }

    private fun signIn(loginRequest: LoginRequest) {
        launch {
            try {
                val userAccount = loginUseCase.execute(loginRequest)

                userAccount?.let {
                    if (hasLoginFailed(it.error)) {
                        state.value = LoginViewState.Error(it.error.message)
                    } else {
                        state.value = LoginViewState.Success(it.userAccount)
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