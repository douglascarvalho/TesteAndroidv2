package com.doug.statement.data

import com.doug.statement.model.Statement
import com.google.gson.annotations.SerializedName


data class StatementResponse (

    @SerializedName("statementList")
    val statements : MutableList<Statement>
)