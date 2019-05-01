package com.doug.statement

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.doug.statement.injection.initializeStatementModule
import com.doug.statement.model.Statement
import com.douglas.actions.extras.Account
import com.douglas.core.BaseActivity
import com.douglas.extensions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private val loadStatementModule by lazy { initializeStatementModule() }

class StatementActivity : BaseActivity() {

    private val statementViewModel: StatementViewModel by viewModel()

    private val account: Account by bindBundle(Account.EXTRA_KEY)

    private val accountOwner: TextView by bindView(R.id.account_owner)
    private val accountNumber: TextView by bindView(R.id.account_number)
    private val accountBalance: TextView by bindView(R.id.account_balance)
    private val logout: ImageView by bindView(R.id.logout)

    private val statementList: RecyclerView by bindView(R.id.statement_list)
    private val statementAdapter by lazy { StatementAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        observeViewModel()

        setupStatementHeader()
        setupStatementList()

        statementViewModel.getStatement(account.id)
    }

    override fun initializeInjection() {
        loadStatementModule
    }

    private fun setupStatementHeader() {
        accountOwner.text = account.name
        accountNumber.text = getString(R.string.account_format, account.agency, account.bankAccount.toBankAccountFormat())
        accountBalance.text = account.balance.toBrazilianCurrency()

        setupLogoutButton()
    }

    private fun setupLogoutButton() {
        logout.onClick {
            finish()
        }
    }

    private fun setupStatementList() {
        statementList.adapter = statementAdapter
    }

    private fun observeViewModel() {
        statementViewModel.viewState.observe(this, Observer {
            when (it) {
                is StatementViewState.Success -> showStatements(it.statements)
            }
        })
    }

    private fun showStatements(statements: MutableList<Statement>) {
        statementAdapter.submitList(statements)
    }
}