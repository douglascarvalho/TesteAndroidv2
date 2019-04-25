package com.douglas.login

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object Login {
    fun init() = loadKoinModules(loginModule)
}

val loginModule = module {
    viewModel { LoginViewModel() }
}