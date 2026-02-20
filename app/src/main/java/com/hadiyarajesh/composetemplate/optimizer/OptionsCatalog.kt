package com.hadiyarajesh.composetemplate.optimizer

import android.content.Intent
import android.net.Uri
import android.provider.Settings

private fun dlSettingsAction(action: String): DeepLink =
    DeepLink(Intent(action))

private fun dlSettingsActionWithData(action: String, uri: Uri): DeepLink =
    DeepLink(Intent(action).apply { data = uri })

private fun dlComponent(pkg: String, cls: String, manufacturer: String? = null): DeepLink =
    DeepLink(Intent().setClassName(pkg, cls), manufacturer = manufacturer)

fun batteryOptionsCatalog(): List<OptimizeOption> {
    return listOf(
        OptimizeOption(
            id = "battery_saver",
            title = "Economia de bateria",
            recommendation = Recommendation.ENABLE,
            summary = "Reduz consumo em segundo plano.",
            details = "Ative quando precisar de mais autonomia. Pode reduzir sincronizações e limitar apps em background.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_BATTERY_SAVER_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "adaptive_brightness",
            title = "Brilho / Tela",
            recommendation = Recommendation.REVIEW,
            summary = "Brilho alto é um dos maiores vilões da bateria.",
            details = "Reduzir brilho, usar brilho automático e diminuir tempo para apagar tela aumenta MUITO a autonomia.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_DISPLAY_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "location",
            title = "Localização (GPS)",
            recommendation = Recommendation.DISABLE,
            summary = "Desative se não estiver usando (impacto alto).",
            details = "GPS e serviços de localização gastam energia. Mantenha ligado só quando precisar.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "wifi",
            title = "Wi-Fi",
            recommendation = Recommendation.REVIEW,
            summary = "Desligue quando não estiver usando; evite busca constante.",
            details = "Wi-Fi ligado buscando rede pode gastar bateria. Se estiver no 4G/5G sem Wi-Fi, desligar ajuda.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_WIFI_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "bluetooth",
            title = "Bluetooth",
            recommendation = Recommendation.REVIEW,
            summary = "Desligue quando não estiver usando.",
            details = "Bluetooth ligado procurando dispositivos pode consumir bateria. Se usa fone/relógio, mantenha ligado.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_BLUETOOTH_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "data_saver",
            title = "Economia de dados",
            recommendation = Recommendation.ENABLE,
            summary = "Reduz atividade em segundo plano (ajuda bateria).",
            details = "Diminuir uso em background reduz wakeups e pode melhorar bateria. Alguns apps podem atrasar notificações.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_DATA_SAVER_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "wireless",
            title = "Conexões (Wi-Fi/BT/Rede)",
            recommendation = Recommendation.REVIEW,
            summary = "Atalho geral para ajustes de conectividade.",
            details = "Use para desligar o que não estiver usando (Wi-Fi/BT/Hotspot).",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_WIRELESS_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "app_notifications",
            title = "Notificações",
            recommendation = Recommendation.REVIEW,
            summary = "Apps com notificação constante costumam rodar mais em background.",
            details = "Reduzir notificações e permissões desnecessárias diminui atividade em segundo plano e economiza bateria.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS),
                dlSettingsAction(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "sync_settings",
            title = "Sincronização",
            recommendation = Recommendation.REVIEW,
            summary = "Sincronizações frequentes gastam bateria.",
            details = "Revise sincronizações (contas, backups, apps). Desativar o que não precisa melhora autonomia.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_SYNC_SETTINGS),
                dlSettingsAction(Settings.ACTION_ADD_ACCOUNT)
            )
        ),
        OptimizeOption(
            id = "battery_optimization",
            title = "Otimização de bateria (por app)",
            recommendation = Recommendation.REVIEW,
            summary = "Controle quais apps podem rodar livremente.",
            details = "Aqui você ajusta apps que ficam em segundo plano. Evite liberar muitos apps sem necessidade.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "apps",
            title = "Gerenciar apps",
            recommendation = Recommendation.REVIEW,
            summary = "Revise apps pesados e com serviços em background.",
            details = "Desinstale apps que você não usa. Revise permissões e consumo em segundo plano.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APPLICATION_SETTINGS)
            )
        ),
        // Xiaomi/MIUI (tentativas seguras: só abre se existir)
        OptimizeOption(
            id = "miui_autostart",
            title = "MIUI: Autostart (inicialização automática)",
            recommendation = Recommendation.REVIEW,
            summary = "Controle quais apps iniciam sozinhos.",
            details = "Em MIUI, muitos apps iniciam em background. Reduzir Autostart pode melhorar bateria.",
            deepLinks = listOf(
                dlComponent(
                    pkg = "com.miui.securitycenter",
                    cls = "com.miui.permcenter.autostart.AutoStartManagementActivity",
                    manufacturer = "Xiaomi"
                )
            )
        ),
        OptimizeOption(
            id = "miui_battery",
            title = "MIUI: Economia de bateria (Security Center)",
            recommendation = Recommendation.REVIEW,
            summary = "Atalho para tela de bateria da MIUI (se existir).",
            details = "Alguns modelos Xiaomi têm controles extras de bateria. Se abrir, use para ajustar apps em segundo plano.",
            deepLinks = listOf(
                dlComponent(
                    pkg = "com.miui.securitycenter",
                    cls = "com.miui.powercenter.PowerMainActivity",
                    manufacturer = "Xiaomi"
                )
            )
        )
    )
}

fun performanceOptionsCatalog(): List<OptimizeOption> {
    return listOf(
        OptimizeOption(
            id = "developer_options",
            title = "Opções do desenvolvedor",
            recommendation = Recommendation.REVIEW,
            summary = "Base para várias otimizações de desempenho.",
            details = "A maioria das opções avançadas (animações, limites de processos, logs, etc.) ficam aqui.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "animation_scales",
            title = "Animações (escala 0.5x ou desligar)",
            recommendation = Recommendation.REVIEW,
            summary = "Deixa o sistema mais “rápido” visualmente.",
            details = "Em Opções do desenvolvedor: Escala de animação da janela / transição / duração do animator. Use 0.5x ou Desativado.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "background_process_limit",
            title = "Limite de processos em segundo plano",
            recommendation = Recommendation.REVIEW,
            summary = "Pode reduzir travadas, mas pode quebrar multitarefa.",
            details = "Em Opções do desenvolvedor: 'Limite de processos em segundo plano'. Ajuste com cuidado (recomendo não ser agressivo).",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "dont_keep_activities",
            title = "Não manter atividades (cuidado)",
            recommendation = Recommendation.REVIEW,
            summary = "Pode acelerar em alguns casos, mas quebra muitos apps.",
            details = "Em Opções do desenvolvedor: 'Não manter atividades'. Geralmente NÃO recomendo.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "storage",
            title = "Armazenamento",
            recommendation = Recommendation.REVIEW,
            summary = "Pouco espaço livre deixa o sistema lento.",
            details = "Manter pelo menos alguns GB livres ajuda a reduzir travadas e melhora atualizações/caches.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_INTERNAL_STORAGE_SETTINGS),
                dlSettingsAction(Settings.ACTION_STORAGE_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "apps_manage",
            title = "Gerenciar apps",
            recommendation = Recommendation.REVIEW,
            summary = "Remova apps pesados e desnecessários.",
            details = "Apps pesados e com serviços em segundo plano reduzem desempenho e bateria.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APPLICATION_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "usage_access",
            title = "Acesso de uso (Usage Access)",
            recommendation = Recommendation.REVIEW,
            summary = "Revise apps com acesso a uso em segundo plano.",
            details = "Alguns apps com 'Acesso de uso' monitoram o sistema e podem impactar desempenho/bateria.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "overlay",
            title = "Permissão de sobreposição (Display over other apps)",
            recommendation = Recommendation.REVIEW,
            summary = "Apps com overlay podem pesar e causar conflitos.",
            details = "Revise quais apps podem desenhar sobre outros apps. Alguns consomem recursos.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            )
        ),
        OptimizeOption(
            id = "accessibility",
            title = "Acessibilidade (serviços ativos)",
            recommendation = Recommendation.REVIEW,
            summary = "Serviços de acessibilidade podem consumir recursos.",
            details = "Se você não usa, desative serviços de acessibilidade de apps suspeitos/dispensáveis.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "vpn",
            title = "VPN",
            recommendation = Recommendation.REVIEW,
            summary = "VPN pode aumentar latência e consumo.",
            details = "Se não estiver usando, desligar VPN pode ajudar desempenho e bateria.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_VPN_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "private_dns",
            title = "DNS privado",
            recommendation = Recommendation.REVIEW,
            summary = "Pode melhorar privacidade; às vezes afeta velocidade.",
            details = "DNS privado pode impactar latência dependendo do provedor. Ajuste se tiver problemas.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_PRIVATE_DNS_SETTINGS)
            )
        ),
        OptimizeOption(
            id = "app_details_tip",
            title = "Dica: ver detalhes de um app pesado",
            recommendation = Recommendation.REVIEW,
            summary = "Abra detalhes do app para forçar parada/desinstalar.",
            details = "Você pode ir em Apps > (app) e revisar bateria, uso em background, permissões e armazenamento.",
            deepLinks = listOf(
                dlSettingsAction(Settings.ACTION_APPLICATION_SETTINGS)
            )
        ),
        // Xiaomi/MIUI: performance (tentativas)
        OptimizeOption(
            id = "miui_performance_mode",
            title = "MIUI: Performance / Game Turbo (se existir)",
            recommendation = Recommendation.REVIEW,
            summary = "Alguns Xiaomi têm ajustes extras para desempenho.",
            details = "Se abrir, use para revisar modo desempenho e restrições de apps.",
            deepLinks = listOf(
                dlComponent(
                    pkg = "com.miui.securitycenter",
                    cls = "com.miui.gamebooster.ui.GameBoosterMainActivity",
                    manufacturer = "Xiaomi"
                )
            )
        )
    )
}
