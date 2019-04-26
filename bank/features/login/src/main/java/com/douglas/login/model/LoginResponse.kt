package com.douglas.login.model

data class LoginResponse (
    val userAccount: UserAccount,
    val error: Error
)