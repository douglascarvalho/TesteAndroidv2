package com.doug.statement.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

private const val USER_ID = "userId"

interface StatementApi {

    @GET("/api/statements/{$USER_ID}")
    fun getStatement(@Path(USER_ID) userId: String): Deferred<Response<StatementResponse>>

}