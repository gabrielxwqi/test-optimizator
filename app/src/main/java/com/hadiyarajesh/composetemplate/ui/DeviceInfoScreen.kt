package com.hadiyarajesh.composetemplate.ui

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceInfoScreen(onBack: () -> Unit) {
    var analyzing by remember { mutableStateOf(true) }

    // Simula "Analisando..." por um curto período (só UI, não precisa permissão)
    LaunchedEffect(Unit) {
        analyzing = true
        kotlinx.coroutines.delay(900)
        analyzing = false
    }

    val info = remember {
        buildDeviceInfo()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Informações do aparelho") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Voltar") } }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ElevatedCard(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Detecção automática", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "Essas informações ajudam o app a escolher os melhores atalhos de configuração para o seu aparelho.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (analyzing) {
                ElevatedCard(Modifier.fillMaxWidth()) {
                    Row(
                        Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CircularProgressIndicator()
                        Column {
                            Text("Analisando...", style = MaterialTheme.typography.titleMedium)
                            Text("Coletando informações do sistema", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            } else {
                ElevatedCard(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Resumo", style = MaterialTheme.typography.titleMedium)
                        Text("${info.manufacturer} • ${info.model}", style = MaterialTheme.typography.bodyLarge)
                        Text("Android ${info.androidRelease} (SDK ${info.sdkInt})", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                ElevatedCard(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Detalhes do sistema", style = MaterialTheme.typography.titleMedium)
                        InfoRow("Fabricante", info.manufacturer)
                        InfoRow("Modelo", info.model)
                        InfoRow("Dispositivo (device)", info.device)
                        InfoRow("Produto (product)", info.product)
                        InfoRow("Hardware", info.hardware)
                        InfoRow("Board", info.board)
                        InfoRow("Brand", info.brand)

                        Spacer(Modifier.height(6.dp))

                        InfoRow("Android (release)", info.androidRelease)
                        InfoRow("SDK", info.sdkInt.toString())
                        InfoRow("Build ID", info.buildId)
                        InfoRow("Build fingerprint", info.fingerprint)

                        Spacer(Modifier.height(6.dp))

                        InfoRow("Kernel (release)", info.kernelRelease)
                        InfoRow("Kernel (version)", info.kernelVersion)
                        InfoRow("ABI(s)", info.abis)
                        InfoRow("Security patch", info.securityPatch)
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value.ifBlank { "—" }, style = MaterialTheme.typography.bodyMedium)
        Divider()
    }
}

private data class DeviceInfo(
    val manufacturer: String,
    val model: String,
    val brand: String,
    val device: String,
    val product: String,
    val hardware: String,
    val board: String,
    val androidRelease: String,
    val sdkInt: Int,
    val buildId: String,
    val fingerprint: String,
    val abis: String,
    val kernelRelease: String,
    val kernelVersion: String,
    val securityPatch: String
)

private fun buildDeviceInfo(): DeviceInfo {
    val abis = runCatching { Build.SUPPORTED_ABIS.joinToString(", ") }.getOrDefault("—")
    val kernelRelease = runCatching { System.getProperty("os.version") ?: "—" }.getOrDefault("—")

    // Build.VERSION.SECURITY_PATCH existe em muitas versões, mas pode falhar em algumas ROMs.
    val secPatch = runCatching { Build.VERSION.SECURITY_PATCH ?: "—" }.getOrDefault("—")

    return DeviceInfo(
        manufacturer = Build.MANUFACTURER ?: "—",
        model = Build.MODEL ?: "—",
        brand = Build.BRAND ?: "—",
        device = Build.DEVICE ?: "—",
        product = Build.PRODUCT ?: "—",
        hardware = Build.HARDWARE ?: "—",
        board = Build.BOARD ?: "—",
        androidRelease = Build.VERSION.RELEASE ?: "—",
        sdkInt = Build.VERSION.SDK_INT,
        buildId = Build.ID ?: "—",
        fingerprint = Build.FINGERPRINT ?: "—",
        abis = abis,
        kernelRelease = kernelRelease,
        kernelVersion = kernelRelease, // (no Android, os.version geralmente já é a versão do kernel)
        securityPatch = secPatch
    )
}
