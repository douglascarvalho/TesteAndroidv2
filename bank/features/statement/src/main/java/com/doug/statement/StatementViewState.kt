package com.doug.statement

import com.doug.statement.model.Statement

sealed class StatementViewState {
    data class Success(val statements: MutableList<Statement>) : StatementViewState()
}