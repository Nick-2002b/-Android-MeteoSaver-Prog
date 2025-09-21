package com.unibo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity: ComponentActivity() {
    override fun onCreate(savedIstanceState: Bundle?) {
        super.onCreate(savedIstanceState)
        setContent {
            NavigationHost()
        }
    }
}