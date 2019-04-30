package com.douglas.login

import com.douglas.actions.extras.Account
import com.douglas.login.model.UserAccount

class LoginMapper {

    fun mapToStatement(userAccount: UserAccount) =
        Account(
            id = userAccount.userId,
            name = userAccount.name,
            bankAccount = userAccount.bankAccount,
            agency = userAccount.agency,
            balance = userAccount.balance
        )

}