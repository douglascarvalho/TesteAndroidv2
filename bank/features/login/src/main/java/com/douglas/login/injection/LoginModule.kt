package com.douglas.login.injection

import androidx.room.Room
import com.douglas.login.LoginMapper
import com.douglas.login.usecase.LoginUseCase
import com.douglas.login.LoginViewModel
import com.douglas.login.data.LoginApi
import com.douglas.login.data.source.LoginDataSource
import com.douglas.login.data.source.LoginRepository
import com.douglas.login.data.source.local.LoginDatabase
import com.douglas.login.data.source.local.LoginLocalDataSource
import com.douglas.login.data.source.remote.LoginRemoteDataSource
import com.douglas.network.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

internal fun initializeLoginModule() = loadKoinModules(loginModule)

val loginModule = module {

    single { Room.databaseBuilder(get(), LoginDatabase::class.java, "login_database").build() }
    single { get<LoginDatabase>().loginDao() }

    single { RetrofitClient.retrofit().create<LoginApi>(LoginApi::class.java) }

    single<LoginDataSource>(named("local")) { LoginLocalDataSource(get()) }
    single<LoginDataSource>(named("remote")) { LoginRemoteDataSource(get()) }
    single { LoginRepository(get(named("local")), get(named("remote"))) }

    factory { LoginUseCase(get()) }
    factory { LoginMapper() }

    viewModel { LoginViewModel(get(), get()) }

}