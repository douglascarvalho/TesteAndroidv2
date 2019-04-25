package com.douglas.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.douglas.core.BaseViewModel
import com.douglas.login.model.Login
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val errorState by lazy { state.value = LoginViewState.Error }
    private val state = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState> = state

    fun authenticate(username: String, password: String) {
        signIn(Login(username, password))
    }

    private fun signIn(login: Login) {
        launch {
            try {
                val userAccount = loginUseCase.execute(login)

                userAccount?.let {
                    state.value = LoginViewState.Success(it)
                } ?: run {
                    errorState
                }
            } catch (e: Exception) {
                errorState
            }
        }
    }

}