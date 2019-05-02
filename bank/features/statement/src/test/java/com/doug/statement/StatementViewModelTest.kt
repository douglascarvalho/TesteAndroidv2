package com.doug.statement

import androidx.lifecycle.Observer
import com.doug.statement.data.StatementResponse
import com.doug.statement.model.Statement
import com.doug.statement.usecase.StatementUseCase
import com.douglas.core.instantLiveDataAndCoroutineRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import java.util.*

class StatementViewModelTest {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRule

    private val observer: Observer<StatementViewState> = mock()
    private val statementUseCase: StatementUseCase = mock()

    private val viewModel = StatementViewModel(statementUseCase)

    @Before
    fun setup() {
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun givenNullStatementResponse_ShouldNotEmitSuccessState() {
        val userId = 7
        val statementResponse: StatementResponse? = null

        whenever(runBlocking {
            statementUseCase.getStatement(userId)
        }) doReturn statementResponse

        viewModel.getStatement(userId)

        verify(observer, never()).onChanged(StatementViewState.Success(anyList()))
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun givenValidStatementResponse_ShouldEmitSuccessState() {
        val userId = 7
        val statements = mutableListOf(
            Statement("Aluguel", "Pagamento", Date(), 1000.toDouble()),
            Statement("Escola", "Pagamento", Date(), 500.toDouble())
        )
        val statementResponse = StatementResponse(statements)

        whenever(runBlocking {
            statementUseCase.getStatement(userId)
        }) doReturn statementResponse

        viewModel.getStatement(userId)

        verify(observer).onChanged(StatementViewState.Success(statements))
        verifyNoMoreInteractions(observer)
    }
}