package com.douglas.bank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.douglas.actions.Actions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Actions.getLoginIntent(this))
    }
}