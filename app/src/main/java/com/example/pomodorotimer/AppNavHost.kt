package com.example.pomodorotimer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pomodorotimer.ui.PomodoroApp
import com.example.pomodorotimer.ui.SettingsScreen
import kotlin.time.Duration.Companion.minutes

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    var workTime by remember { mutableStateOf(25.minutes) }
    var breakTime by remember { mutableStateOf(5.minutes) }

    NavHost(
        navController = navController,
        startDestination = "timer"
    ) {
        composable("timer") {
            PomodoroApp(
                workTime = workTime,
                breakTime = breakTime,
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }

        composable("settings") {
            SettingsScreen(
                initialWorkTime = workTime,
                initialBreakTime = breakTime,
                onSave = { newWorkTime, newBreakTime ->
                    workTime = newWorkTime
                    breakTime = newBreakTime

                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}


