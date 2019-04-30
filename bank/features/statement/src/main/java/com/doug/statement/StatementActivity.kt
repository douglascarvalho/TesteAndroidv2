package com.doug.statement

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doug.statement.injection.initializeStatementModule
import com.doug.statement.model.Statement
import com.douglas.actions.extras.Account
import com.douglas.extensions.bindBundle
import com.douglas.extensions.bindView
import org.koin.androidx.viewmodel.ext.android.viewModel

private val loadStatement by lazy { initializeStatementModule() }
private fun injectStatement() = loadStatement

class StatementActivity : AppCompatActivity() {

    private val statementViewModel: StatementViewModel by viewModel()

    private val account: Account by bindBundle(Account.EXTRA_KEY)

    private val accountOwner: TextView by bindView(R.id.account_owner)
    private val accountNumber: TextView by bindView(R.id.account_number)
    private val accountBalance: TextView by bindView(R.id.account_balance)

    private val statementList: RecyclerView by bindView(R.id.statement_list)
    private val statementAdapter by lazy { StatementAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        injectStatement()
        observeViewModel()
        setupStatementList()

        accountOwner.text = account.name
        accountNumber.text = "${account.bankAccount} / ${account.agency}"
        accountBalance.text = account.balance

        statementViewModel.getStatement(account.id)
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