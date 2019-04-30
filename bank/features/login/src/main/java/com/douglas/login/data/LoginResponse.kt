package com.douglas.login.data

import com.douglas.login.model.Error
import com.douglas.login.model.UserAccount

data class LoginResponse (
    val userAccount: UserAccount,
    val error: Error
)