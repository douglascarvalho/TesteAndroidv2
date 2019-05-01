package com.douglas.login

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import com.douglas.actions.Actions
import com.douglas.actions.extras.Account
import com.douglas.core.BaseActivity
import com.douglas.extensions.bindView
import com.douglas.extensions.onClick
import com.douglas.login.injection.initializeLoginModule
import com.douglas.login.model.Error
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

private val loadLoginModule by lazy { initializeLoginModule() }

class LoginActivity : BaseActivity() {

    private val progressBar: ProgressBar by bindView(R.id.progressBar)
    private val networkErrorMessage: TextView by bindView(R.id.networkErrorMessage)
    private val usernameLayout: TextInputLayout by bindView(R.id.username_layout)
    private val passwordLayout: TextInputLayout by bindView(R.id.password_layout)
    private val username: TextInputEditText by bindView(R.id.username)
    private val password: TextInputEditText by bindView(R.id.password)
    private val loginButton: MaterialButton by bindView(R.id.login)

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        observeViewModel()

        setupLoginButton()
    }

    override fun onResume() {
        super.onResume()
        clearInputValues()
        loginViewModel.getLastLoggeduser()
    }

    override fun initializeInjection() {
        loadLoginModule
    }

    private fun setupLoginButton() {
        loginButton.onClick {
            showLoading()
            clearErrorMessages()
            loginViewModel.authenticate(username.text.toString(), password.text.toString())
        }
    }

    private fun observeViewModel() {
        loginViewModel.viewState.observe(this, Observer {
            hideLoading()
            when (it) {
                is LoginViewState.Success -> loginSuccessful(it.account)
                is LoginViewState.Error -> loginError(it.error)
                is LoginViewState.NetworkError -> networkError()
                is LoginViewState.SuggestLastLoggedUser -> suggestLastLoggedUser(it.user)
                is LoginViewState.InvalidUsername -> invalidUserName()
                is LoginViewState.WeakPassword -> weakPassword()
            }
        })
    }

    private fun suggestLastLoggedUser(user: String) {
        this.username.setText(user)
        this.password.requestFocus()
    }

    private fun loginSuccessful(account: Account) {
        val intent = Actions.getStatementIntent(this)
        intent.putExtra(Account.EXTRA_KEY, account)
        startActivity(intent)
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
        networkErrorMessage.visibility = View.VISIBLE
    }

    private fun clearErrorMessages() {
        usernameLayout.isErrorEnabled = false
        passwordLayout.isErrorEnabled = false
        networkErrorMessage.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun clearInputValues() {
        username.text?.clear()
        password.text?.clear()
    }
}
