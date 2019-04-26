package com.douglas.login

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.douglas.extensions.bindView
import com.douglas.extensions.onClick
import com.douglas.login.injection.initializeLoginModule
import com.douglas.login.model.Error
import com.douglas.login.model.UserAccount
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

private val loadLogin by lazy { initializeLoginModule() }
private fun injectLogin() = loadLogin

class LoginActivity : AppCompatActivity() {

    private val progressBar: ProgressBar by bindView(R.id.progressBar)
    private val networkErrorMesagge: TextView by bindView(R.id.networkErrorMessage)
    private val usernameLayout: TextInputLayout by bindView(R.id.username_layout)
    private val passwordLayout: TextInputLayout by bindView(R.id.password_layout)
    private val username: TextInputEditText by bindView(R.id.username)
    private val password: TextInputEditText by bindView(R.id.password)
    private val loginButton: MaterialButton by bindView(R.id.login)

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        injectLogin()
        observeViewModel()

        loginButton.onClick {
            showLoading()
            clearErrorMessages()
            loginViewModel.authenticate(username.text.toString(), password.text.toString())
        }
    }

    private fun observeViewModel() {
        loginViewModel.viewState.observe(this, Observer {
            when (it) {
                is LoginViewState.Success -> loginSuccessful(it.userAccount)
                is LoginViewState.Error -> loginError(it.error)
                is LoginViewState.NetworkError -> networkError()
                is LoginViewState.InvalidUsername -> invalidUserName()
                is LoginViewState.WeakPassword -> weakPassword()
            }

            hideLoading()
        })
    }

    private fun loginSuccessful(userAccount: UserAccount) {

        Toast.makeText(this, "SUCCESS ${userAccount.name}", Toast.LENGTH_LONG).show()
    }

    private fun loginError(error: Error) {
        usernameLayout.error = error.message
        passwordLayout.error = error.message
    }

    private fun invalidUserName() {
        usernameLayout.error = getString(R.string.username_validation_error)
    }

    private fun weakPassword() {
        passwordLayout.error = getString(R.string.password_validation_error)
    }

    private fun networkError() {
        networkErrorMesagge.visibility = View.VISIBLE
    }

    private fun clearErrorMessages() {
        usernameLayout.isErrorEnabled = false
        passwordLayout.isErrorEnabled = false
        networkErrorMesagge.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

}
