package com.example.pomodorotimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pomodorotimer.navigation.AppNavHost
import com.example.pomodorotimer.ui.theme.PomodoroTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroTimerTheme {
                AppNavHost()
            }
        }
    }
}
