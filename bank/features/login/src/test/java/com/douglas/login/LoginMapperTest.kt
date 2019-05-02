package com.douglas.login

import com.douglas.login.model.UserAccount
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginMapperTest {

    @Test
    fun givenUserAccount_mapToStatement_ShouldReturnAccount() {

        val userAccount = UserAccount(1, "doug", "111", "22222", 12000.toDouble())

        val account = LoginMapper().mapToStatement(userAccount)

        assertEquals(userAccount.userId, account.id)
        assertEquals(userAccount.name, account.name)
        assertEquals(userAccount.agency, account.agency)
        assertEquals(userAccount.bankAccount, account.bankAccount)
        assertEquals(userAccount.balance, account.balance, 0.toDouble())
    }

}