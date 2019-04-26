package com.douglas.login.injection

import com.douglas.login.usecase.LoginUseCase
import com.douglas.login.LoginViewModel
import com.douglas.login.data.LoginApi
import com.douglas.network.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun initializeLoginModule() = loadKoinModules(loginModule)

val loginModule = module {

    single { RetrofitClient.retrofit().create<LoginApi>(LoginApi::class.java) }

    factory { LoginUseCase(get()) }

    viewModel { LoginViewModel(get()) }

}