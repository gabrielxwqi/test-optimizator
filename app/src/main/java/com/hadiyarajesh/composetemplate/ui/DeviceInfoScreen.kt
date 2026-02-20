package com.hadiyarajesh.composetemplate.ui

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeviceInfoScreen(
    onBack: () -> Unit
) {
    // Lista em pares (rótulo, valor) para garantir scroll e layout consistente
    val info = remember {
        listOf(
            "Fabricante" to Build.MANUFACTURER,
            "Marca (brand)" to Build.BRAND,
            "Modelo" to Build.MODEL,
            "Dispositivo (device)" to Build.DEVICE,
            "Produto (product)" to Build.PRODUCT,
            "Hardware" to Build.HARDWARE,
            "Board" to Build.BOARD,
            "Android (release)" to Build.VERSION.RELEASE,
            "SDK" to Build.VERSION.SDK_INT.toString(),
            "Incremental" to (Build.VERSION.INCREMENTAL ?: ""),
            "Fingerprint" to (Build.FINGERPRINT ?: "")
        )
    }

    Scaffold(
        topBar = {
            Surface(tonalElevation = 8.dp) {
                Column(Modifier.fillMaxWidth().padding(16.dp)) {
                    Row {
                        TextButton(onClick = onBack) { Text("Voltar") }
                        Spacer(Modifier.width(8.dp))
                        Text("Informações do aparelho", style = MaterialTheme.typography.titleLarge)
                    }
                    Text(
                        "Essas informações ajudam o app a escolher atalhos mais compatíveis com seu aparelho.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Resumo", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "${Build.MANUFACTURER} • ${Build.MODEL}\nAndroid ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Detalhes do sistema", style = MaterialTheme.typography.titleMedium)

                        info.forEach { (k, v) ->
                            Column {
                                Text(k, style = MaterialTheme.typography.labelMedium)
                                Text(v.ifBlank { "—" }, style = MaterialTheme.typography.bodyMedium)
                                Spacer(Modifier.height(10.dp))
                                Divider()
                                Spacer(Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}
