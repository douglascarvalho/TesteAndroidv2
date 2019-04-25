package com.douglas.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.douglas.login.injection.initializeLoginModule
import org.koin.androidx.viewmodel.ext.android.viewModel

private val loadLogin by lazy { initializeLoginModule() }
private fun injectLogin() = loadLogin

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        injectLogin()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, loginViewModel.getDoug(), Toast.LENGTH_LONG).show()
    }
}
