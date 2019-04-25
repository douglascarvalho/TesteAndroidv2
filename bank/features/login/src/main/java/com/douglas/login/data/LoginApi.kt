package com.douglas.login.data

import com.douglas.login.model.Login
import com.douglas.login.model.UserAccount
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/api/login")
    fun login(@Body login: Login): Deferred<Response<UserAccount>>

}