package com.doug.statement

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.douglas.actions.extras.Account
import com.douglas.extensions.bindBundle
import com.douglas.extensions.bindView

class StatementActivity : AppCompatActivity() {

    private val account: Account by bindBundle(Account.EXTRA_KEY)

    private val accountOwner: TextView by bindView(R.id.account_owner)
    private val accountNumber: TextView by bindView(R.id.account_number)
    private val accountBalance: TextView by bindView(R.id.account_balance)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        accountOwner.text = account.name
        accountNumber.text = "${account.bankAccount} / ${account.agency}"
        accountBalance.text = account.balance
    }

}