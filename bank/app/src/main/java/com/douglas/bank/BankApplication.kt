package com.douglas.bank

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BankApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BankApplication)
        }
    }
}