package com.hadiyarajesh.composetemplate.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.composetemplate.optimizer.OptimizeOption
import com.hadiyarajesh.composetemplate.optimizer.openBestDeepLink

@Composable
fun OptimizerScreen(
    title: String,
    options: List<OptimizeOption>,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var selected by remember { mutableStateOf<OptimizeOption?>(null) }
    val doneMap = remember { mutableStateMapOf<String, Boolean>() }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBack) { Text("Voltar") }
            Spacer(Modifier.width(8.dp))
            Text(title, style = MaterialTheme.typography.headlineSmall)
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                // Manual: não aplica sozinho. (Aqui depois a gente pode ordenar/priorizar.)
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Otimizar (manual)") }

        Spacer(Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(options) { opt ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(opt.title, style = MaterialTheme.typography.titleMedium)
                            Checkbox(
                                checked = doneMap[opt.id] == true,
                                onCheckedChange = { doneMap[opt.id] = it }
                            )
                        }

                        Text(opt.summary, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(onClick = { context.openBestDeepLink(opt) }) {
                                Text("Ir para configuração")
                            }
                            Text(
                                "Detalhes",
                                modifier = Modifier
                                    .clickable { selected = opt }
                                    .padding(10.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }

    if (selected != null) {
        AlertDialog(
            onDismissRequest = { selected = null },
            confirmButton = {
                TextButton(onClick = { selected = null }) { Text("Fechar") }
            },
            title = { Text(selected!!.title) },
            text = { Text(selected!!.details) }
        )
    }
}
