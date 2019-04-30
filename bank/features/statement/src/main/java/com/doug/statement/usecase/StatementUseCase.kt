package com.doug.statement.usecase

import com.doug.statement.data.StatementApi
import com.doug.statement.data.StatementResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatementUseCase (
    private val statementApi: StatementApi
) {

    suspend fun getStatement(id: String) : StatementResponse? = withContext(Dispatchers.IO) {
        val response = statementApi.getStatement(id).await()
        return@withContext if (response.isSuccessful) response.body() else null
    }

}