package com.douglas.actions

import android.content.Context
import android.content.Intent

object Actions {

    fun getLoginIntent(context: Context): Intent = internalIntent(context, "com.douglas.intent.action.LOGIN")
    fun getStatementIntent(context: Context): Intent = internalIntent(context, "com.douglas.intent.action.STATEMENT")

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}