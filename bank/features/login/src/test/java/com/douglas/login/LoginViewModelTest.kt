package com.douglas.login

import androidx.lifecycle.Observer
import com.douglas.actions.extras.Account
import com.douglas.core.instantLiveDataAndCoroutineRule
import com.douglas.login.data.LastLoggedUser
import com.douglas.login.data.LoginRequest
import com.douglas.login.data.LoginResponse
import com.douglas.login.model.Error
import com.douglas.login.model.UserAccount
import com.douglas.login.usecase.LoginUseCase
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.*

class LoginViewModelTest {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRule

    private val observer: Observer<LoginViewState> = mock()
    private val loginUseCase: LoginUseCase = mock()
    private val loginMapper: LoginMapper = mock()

    private val viewModel = LoginViewModel(loginUseCase, loginMapper)

    private val userAccount = UserAccount(1, "doug", "111", "22222", 12000.toDouble())
    private val filledError = Error(1, "error")
    private val emptyError = Error(0, "")

    @Before
    fun setup() {
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun givenNullLastLoggeduser_ShouldNotEmitSuggestLastLoggedUserState() {
        whenever(runBlocking {
            loginUseCase.getLastLoggedUser()
        }) doReturn null

        viewModel.getLastLoggeduser()

        verify(observer, never()).onChanged(LoginViewState.SuggestLastLoggedUser(anyString()))
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenValidLastLoggeduser_ShouldEmitSuggestLastLoggedUserState() {

        val lastLoggedUser = LastLoggedUser(1, "doug", Date())

        whenever(runBlocking {
            loginUseCase.getLastLoggedUser()
        }) doReturn lastLoggedUser

        viewModel.getLastLoggeduser()

        verify(observer).onChanged(LoginViewState.SuggestLastLoggedUser(lastLoggedUser.user))
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenEmptyUserNameAndPassword_authenticate_ShouldEmitInvalidUserNameAndInvalidPassword() {
        val username = ""
        val password = ""

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.InvalidUsername)
        verify(observer).onChanged(LoginViewState.WeakPassword)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenInvalidUserNameAndPassword_authenticate_ShouldEmitInvalidUserNameAndInvalidPassword() {
        val username = "username"
        val password = "password"

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.InvalidUsername)
        verify(observer).onChanged(LoginViewState.WeakPassword)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenPasswordWithoutCapitalLetter_authenticate_ShouldEmitInvalidPassword() {
        val username = "douglas@gmail.com"
        val password = "rfd$4"

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.WeakPassword)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenPasswordWithoutSpecialCharacter_authenticate_ShouldEmitInvalidPassword() {
        val username = "douglas@gmail.com"
        val password = "rfd4R"

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.WeakPassword)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenPasswordWithoutNumber_authenticate_ShouldEmitInvalidPassword() {
        val username = "douglas@gmail.com"
        val password = "rfd%T$"

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.WeakPassword)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenNullLoginResponse_authenticate_ShouldEmitNetworkErrorState() {
        val username = "douglas@gmail.com"
        val password = "rfd*T4$4"

        val loginRequeset = LoginRequest(username, password)

        whenever(runBlocking {
            loginUseCase.doLogin(loginRequeset)
        }) doReturn null

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.NetworkError)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenLoginResponseWithError_authenticate_ShouldEmitErrorState() {
        val username = "douglas@gmail.com"
        val password = "rfd*T4$4"

        val loginResponse = LoginResponse(userAccount, filledError)
        val loginRequest = LoginRequest(username, password)

        whenever(runBlocking {
            loginUseCase.doLogin(loginRequest)
        }) doReturn loginResponse

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.Error(filledError))
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenLoginResponseWithoutError_authenticate_ShouldEmitSuccessState() {
        val username = "douglas@gmail.com"
        val password = "rfd*T4$4"

        val loginResponse = LoginResponse(userAccount, emptyError)
        val loginRequest = LoginRequest(username, password)
        val account = Account(1, "Doug", "111", "2222", 12000.toDouble())

        whenever(runBlocking {
            loginUseCase.doLogin(loginRequest)
        }) doReturn loginResponse

        whenever(
            loginMapper.mapToStatement(userAccount)
        ) doReturn account

        viewModel.authenticate(username, password)

        verify(observer).onChanged(LoginViewState.Success(account))
        verifyNoMoreInteractions(observer)
    }

}