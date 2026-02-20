package com.hadiyarajesh.composetemplate.optimizer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

enum class Recommendation { ENABLE, DISABLE, REVIEW }

data class DeepLink(
    val intent: Intent,
    val manufacturer: String? = null,
    val minSdk: Int? = null,
    val maxSdk: Int? = null
)

data class OptimizeOption(
    val id: String,
    val title: String,
    val recommendation: Recommendation,
    val summary: String,
    val details: String,
    val deepLinks: List<DeepLink>
)

fun Context.openBestDeepLink(option: OptimizeOption): Boolean {
    val m = Build.MANUFACTURER.lowercase()
    val sdk = Build.VERSION.SDK_INT

    val candidates = option.deepLinks.filter { dl ->
        val manOk = dl.manufacturer?.lowercase()?.let { it == m } ?: true
        val minOk = dl.minSdk?.let { sdk >= it } ?: true
        val maxOk = dl.maxSdk?.let { sdk <= it } ?: true
        manOk && minOk && maxOk
    }

    for (dl in candidates) {
        val intent = dl.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            return true
        }
    }
    return false
}

fun appDetailsIntent(packageName: String): Intent {
    return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.parse("package:$packageName")
    }
}
