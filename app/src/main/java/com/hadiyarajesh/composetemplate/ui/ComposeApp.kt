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
                subtitle = "Checklist manual com atalhos e explicações.",
                options = batteryOptionsCatalog(),
                onBack = { screen = Screen.HOME }
            )

            Screen.PERFORMANCE -> OptimizerScreen(
                title = "Desempenho",
                subtitle = "Ajustes avançados (com cautela).",
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
        topBar = { PremiumTopBar(title = "Otimizador") }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PremiumInfoCard(
                title = "Perfis manuais",
                body = "Checklist guiado com atalhos para as telas corretas do sistema. Nada é alterado automaticamente."
            )

            PremiumActionCard(
                title = "Bateria",
                body = "Aumente a duração com ajustes manuais recomendados.",
                buttonText = "Abrir checklist de Bateria",
                onClick = onBattery
            )

            PremiumActionCard(
                title = "Desempenho",
                body = "Ajustes avançados e Opções do desenvolvedor (com cautela).",
                buttonText = "Abrir checklist de Desempenho",
                onClick = onPerformance
            )

            OutlinedButton(
                onClick = onDeviceInfo,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver informações do aparelho")
            }

            PremiumInfoCard(
                title = "Dica",
                body = "Se algum atalho não abrir no seu aparelho, use a explicação e procure a opção manualmente no menu equivalente."
            )
        }
    }
}

@Composable
private fun PremiumTopBar(title: String) {
    Surface(
        tonalElevation = 8.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
private fun PremiumInfoCard(title: String, body: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun PremiumActionCard(
    title: String,
    body: String,
    buttonText: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(body, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
                Text(buttonText)
            }
        }
    }
}
