package com.hadiyarajesh.composetemplate.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.composetemplate.optimizer.batteryOptionsCatalog
import com.hadiyarajesh.composetemplate.optimizer.performanceOptionsCatalog
import com.hadiyarajesh.composetemplate.ui.theme.AppTheme

private enum class Screen { HOME, BATTERY, PERFORMANCE }

@Composable
fun ComposeApp() {
    AppTheme {
        var screen by remember { mutableStateOf(Screen.HOME) }

        when (screen) {
            Screen.HOME -> Column(
                Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Otimizador", style = MaterialTheme.typography.headlineMedium)
                Button(onClick = { screen = Screen.BATTERY }, modifier = Modifier.fillMaxWidth()) {
                    Text("Bateria")
                }
                Button(onClick = { screen = Screen.PERFORMANCE }, modifier = Modifier.fillMaxWidth()) {
                    Text("Desempenho")
                }
            }

            Screen.BATTERY -> OptimizerScreen(
                title = "Bateria",
                options = batteryOptionsCatalog(),
                onBack = { screen = Screen.HOME }
            )

            Screen.PERFORMANCE -> OptimizerScreen(
                title = "Desempenho",
                options = performanceOptionsCatalog(),
                onBack = { screen = Screen.HOME }
            )
        }
    }
}
