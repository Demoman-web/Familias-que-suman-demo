package com.familiasquesuman.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.familiasquesuman.demo.ui.AppNav
import com.familiasquesuman.demo.ui.theme.FamiliasQueSumanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FamiliasQueSumanTheme {
                AppNav()
            }
        }
    }
}
