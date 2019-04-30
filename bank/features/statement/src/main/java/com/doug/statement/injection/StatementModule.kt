package com.doug.statement.injection

import com.doug.statement.StatementViewModel
import com.doug.statement.data.StatementApi
import com.doug.statement.usecase.StatementUseCase
import com.douglas.network.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun initializeStatementModule() = loadKoinModules(statementModule)

val statementModule = module {

    single { RetrofitClient.retrofit().create<StatementApi>(StatementApi::class.java) }

    factory { StatementUseCase(get()) }

    viewModel { StatementViewModel(get()) }

}