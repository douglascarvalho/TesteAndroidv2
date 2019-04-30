package com.doug.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doug.statement.usecase.StatementUseCase
import com.douglas.core.BaseViewModel
import kotlinx.coroutines.launch

class StatementViewModel(
    private val statementUseCase: StatementUseCase
) : BaseViewModel() {

    private val state = MutableLiveData<StatementViewState>()
    val viewState: LiveData<StatementViewState> = state

    fun getStatement(id: String) {
        launch {
            val statementResponse = statementUseCase.getStatement(id)
            statementResponse?.let {
                state.value = StatementViewState.Success(it.statements)
            }
        }
    }

}