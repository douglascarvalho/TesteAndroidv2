package com.douglas.login.model

data class UserAccount(
    val userId: String,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: String
)