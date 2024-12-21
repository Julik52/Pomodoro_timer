package com.example.pomodorotimer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    initialWorkTime: Duration,
    initialBreakTime: Duration,
    onSave: (Duration, Duration) -> Unit,
    onBack: () -> Unit
) {
    var workTime by remember { mutableStateOf(initialWorkTime) }
    var breakTime by remember { mutableStateOf(initialBreakTime) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Adjust Timer Durations", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Work Time: ${workTime.inWholeMinutes} min", modifier = Modifier.weight(1f))
                    Slider(
                        value = workTime.inWholeMinutes.toFloat(),
                        onValueChange = { workTime = it.toInt().minutes },
                        valueRange = 5f..60f,
                        modifier = Modifier.weight(3f)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Break Time: ${breakTime.inWholeMinutes} min", modifier = Modifier.weight(1f))
                    Slider(
                        value = breakTime.inWholeMinutes.toFloat(),
                        onValueChange = { breakTime = it.toInt().minutes },
                        valueRange = 1f..30f,
                        modifier = Modifier.weight(3f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { onSave(workTime, breakTime) }) {
                    Text("Save Changes")
                }

            }
        }
    )
}

