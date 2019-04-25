package com.douglas.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.douglas.extensions.bindView
import com.douglas.extensions.onClick
import com.douglas.login.injection.initializeLoginModule
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

private val loadLogin by lazy { initializeLoginModule() }
private fun injectLogin() = loadLogin

class LoginActivity : AppCompatActivity() {

    private val username: TextInputEditText by bindView(R.id.username)
    private val password: TextInputEditText by bindView(R.id.password)
    private val loginButton: MaterialButton by bindView(R.id.login)
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        injectLogin()
        observe()

        loginButton.onClick {
            loginViewModel.authenticate(username.text.toString(), password.text.toString())
        }
    }

    private fun observe() {
        loginViewModel.viewState.observe(this, Observer {
            when (it) {
                is LoginViewState.Success -> Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
                is LoginViewState.Error -> Toast.makeText(this, "FAILED", Toast.LENGTH_LONG).show()
            }
        })
    }

}
