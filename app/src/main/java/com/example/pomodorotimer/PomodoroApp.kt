package com.example.pomodorotimer.ui

import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import formatTime
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroApp(
    workTime: Duration,
    breakTime: Duration,
    onNavigateToSettings: () -> Unit
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)

    var remainingTime by remember { mutableStateOf(workTime) }
    var isTimerRunning by remember { mutableStateOf(false) }
    var isWorkSession by remember { mutableStateOf(true) }

    val backgroundColor = if (isWorkSession) Color(0xFFADD8E6) else Color(0xFFFFC0CB) // Блакитний / Рожевий
    val buttonColor = Color.White
    val buttonTextColor = Color.Black

    LaunchedEffect(isTimerRunning, remainingTime) {
        if (isTimerRunning && remainingTime > Duration.ZERO) {
            delay(1000)
            remainingTime -= 1.seconds
        } else if (isTimerRunning && remainingTime == Duration.ZERO) {

            vibrator?.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))

            isWorkSession = !isWorkSession
            remainingTime = if (isWorkSession) workTime else breakTime
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pomodoro Timer") },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isWorkSession) "Work Session" else "Break Session",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = formatTime(remainingTime),
                style = MaterialTheme.typography.displayLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { isTimerRunning = !isTimerRunning },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor,
                        contentColor = buttonTextColor
                    )
                ) {
                    Text(if (isTimerRunning) "Pause" else "Start")
                }
                Button(
                    onClick = {
                        isTimerRunning = false
                        remainingTime = if (isWorkSession) workTime else breakTime
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor,
                        contentColor = buttonTextColor
                    )
                ) {
                    Text("Reset")
                }
            }
        }
    }
}
