package com.hadiyarajesh.composetemplate.ui

import android.widget.Toast
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
    subtitle: String,
    options: List<OptimizeOption>,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var selected by remember { mutableStateOf<OptimizeOption?>(null) }
    val doneMap = remember { mutableStateMapOf<String, Boolean>() }

    Scaffold(
        topBar = {
            Surface(tonalElevation = 8.dp) {
                Column(Modifier.fillMaxWidth().padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(onClick = onBack) { Text("Voltar") }
                        Spacer(Modifier.width(8.dp))
                        Text(title, style = MaterialTheme.typography.titleLarge)
                    }
                    Text(subtitle, style = MaterialTheme.typography.bodyMedium)
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
            items(options) { opt ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(opt.title, style = MaterialTheme.typography.titleMedium)
                                Text(opt.summary, style = MaterialTheme.typography.bodyMedium)
                            }
                            Checkbox(
                                checked = doneMap[opt.id] == true,
                                onCheckedChange = { doneMap[opt.id] = it }
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Button(
                                onClick = {
                                    val ok = context.openBestDeepLink(opt)
                                    if (!ok) {
                                        Toast.makeText(
                                            context,
                                            "Não consegui abrir um atalho direto neste aparelho. Use a explicação e procure a opção manualmente.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            ) { Text("Abrir") }

                            OutlinedButton(onClick = { selected = opt }) {
                                Text("Detalhes")
                            }
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(8.dp)) }
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
}
