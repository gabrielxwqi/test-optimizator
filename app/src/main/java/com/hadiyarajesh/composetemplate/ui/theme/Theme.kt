package com.hadiyarajesh.composetemplate.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Paleta dark “premium” (sem dynamic color para ficar consistente)
private val PremiumDark = darkColorScheme(
    primary = Color(0xFFAEC6FF),
    onPrimary = Color(0xFF0B1220),
    secondary = Color(0xFFB8C8DA),
    tertiary = Color(0xFFC9B7FF),

    background = Color(0xFF0A0F1A),
    onBackground = Color(0xFFE8EEF9),

    surface = Color(0xFF0F1626),
    onSurface = Color(0xFFE8EEF9),

    surfaceVariant = Color(0xFF141E33),
    onSurfaceVariant = Color(0xFFC9D6F0),

    outline = Color(0xFF2A3A5A)
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PremiumDark,
        typography = Typography,
        content = content
    )
}
