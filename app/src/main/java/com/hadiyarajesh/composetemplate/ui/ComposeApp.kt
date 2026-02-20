package com.hadiyarajesh.composetemplate.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.composetemplate.optimizer.batteryOptionsCatalog
import com.hadiyarajesh.composetemplate.optimizer.performanceOptionsCatalog
import com.hadiyarajesh.composetemplate.ui.theme.AppTheme

private enum class Screen { HOME, BATTERY, PERFORMANCE, DEVICE_INFO }

@Composable
fun ComposeApp() {
    AppTheme {
        var screen by remember { mutableStateOf(Screen.HOME) }

        when (screen) {
            Screen.HOME -> HomePremium(
                onBattery = { screen = Screen.BATTERY },
                onPerformance = { screen = Screen.PERFORMANCE },
                onDeviceInfo = { screen = Screen.DEVICE_INFO }
            )

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

            Screen.DEVICE_INFO -> DeviceInfoScreen(
                onBack = { screen = Screen.HOME }
            )
        }
    }
}

@Composable
private fun HomePremium(
    onBattery: () -> Unit,
    onPerformance: () -> Unit,
    onDeviceInfo: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Otimizador") }) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ElevatedCard(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Perfis manuais", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "Checklist guiado com atalhos para as telas corretas do sistema.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            ElevatedCard(modifier = Modifier.fillMaxWidth(), onClick = onBattery) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Bateria", style = MaterialTheme.typography.titleLarge)
                    Text("Aumente a duração com ajustes manuais recomendados.", style = MaterialTheme.typography.bodyMedium)
                }
            }

            ElevatedCard(modifier = Modifier.fillMaxWidth(), onClick = onPerformance) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Desempenho", style = MaterialTheme.typography.titleLarge)
                    Text("Ajustes avançados e opções do desenvolvedor (com cautela).", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(Modifier.height(4.dp))

            // Botão "em baixo" como você pediu
            OutlinedButton(
                onClick = onDeviceInfo,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver informações do aparelho")
            }

            ElevatedCard(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Dica", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "O app não altera configurações sozinho. Ele te leva direto ao lugar certo e explica cada opção.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
