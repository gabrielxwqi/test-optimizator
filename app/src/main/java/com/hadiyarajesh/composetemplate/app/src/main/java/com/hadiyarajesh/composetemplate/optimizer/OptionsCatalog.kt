package com.hadiyarajesh.composetemplate.optimizer

import android.content.Intent
import android.provider.Settings

fun batteryOptionsCatalog(): List<OptimizeOption> {
    return listOf(
        OptimizeOption(
            id = "battery_saver",
            title = "Economia de bateria",
            recommendation = Recommendation.ENABLE,
            summary = "Ajuda a reduzir consumo em segundo plano.",
            details = "A economia de bateria limita atividades em segundo plano e pode reduzir sincronizações, aumentando a autonomia.",
            deepLinks = listOf(
                DeepLink(Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS))
            )
        ),
        OptimizeOption(
            id = "battery_optimization",
            title = "Otimização de bateria por app",
            recommendation = Recommendation.REVIEW,
            summary = "Revise apps que rodam em segundo plano sem necessidade.",
            details = "Você pode restringir apps que gastam bateria em background. Cuidado: isso pode atrasar notificações de apps importantes.",
            deepLinks = listOf(
                DeepLink(Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS))
            )
        ),
        OptimizeOption(
            id = "location",
            title = "Localização",
            recommendation = Recommendation.DISABLE,
            summary = "Desative se não estiver usando (alto impacto).",
            details = "GPS e serviços de localização podem consumir energia. Você pode manter ligado só quando precisar.",
            deepLinks = listOf(
                DeepLink(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            )
        )
    )
}

fun performanceOptionsCatalog(): List<OptimizeOption> {
    return listOf(
        OptimizeOption(
            id = "storage",
            title = "Armazenamento",
            recommendation = Recommendation.REVIEW,
            summary = "Manter espaço livre ajuda o sistema.",
            details = "Pouco armazenamento pode degradar desempenho. Limpe arquivos grandes e cache quando necessário.",
            deepLinks = listOf(
                DeepLink(Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS)),
                DeepLink(Intent(Settings.ACTION_STORAGE_SETTINGS))
            )
        ),
        OptimizeOption(
            id = "app_settings",
            title = "Gerenciar apps",
            recommendation = Recommendation.REVIEW,
            summary = "Desinstale/pare apps pesados e desnecessários.",
            details = "Apps pesados e com serviços em segundo plano podem reduzir desempenho e bateria. Avalie o que é essencial.",
            deepLinks = listOf(
                DeepLink(Intent(Settings.ACTION_APPLICATION_SETTINGS))
            )
        )
    )
}
