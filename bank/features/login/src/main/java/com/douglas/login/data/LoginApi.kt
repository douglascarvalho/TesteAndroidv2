package com.douglas.login.data

import com.douglas.login.model.LoginRequest
import com.douglas.login.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest): Deferred<Response<LoginResponse>>

}