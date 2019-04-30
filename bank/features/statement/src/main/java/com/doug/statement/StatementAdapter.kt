package com.doug.statement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.doug.statement.model.Statement
import com.douglas.extensions.bindView

class StatementAdapter : ListAdapter<Statement, StatementAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val statementTitle: TextView by bindView(R.id.statement_title)
        private val statementDate: TextView by bindView(R.id.statement_date)
        private val statementDescription: TextView by bindView(R.id.statement_description)
        private val statementValue: TextView by bindView(R.id.statement_value)

        fun bindView(item: Statement) {
            statementTitle.text = item.title
            statementDate.text = item.date.toString()
            statementDescription.text = item.description
            statementValue.text = item.value.toString()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Statement>() {
        override fun areItemsTheSame(oldItem: Statement, newItem: Statement) = oldItem === newItem
        override fun areContentsTheSame(oldItem: Statement, newItem: Statement) = oldItem == newItem
    }
}