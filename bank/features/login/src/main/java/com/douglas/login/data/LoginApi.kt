package com.douglas.login.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest): Deferred<Response<LoginResponse>>

}