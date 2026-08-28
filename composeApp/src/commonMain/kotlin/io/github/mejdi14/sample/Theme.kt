package io.github.mejdi14.sample

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class DemoColors(
    val background: Color,
    val surface: Color,
    val onSurface: Color,
    val muted: Color,
    val accent: Color,
    val accentSoft: Color,
    val outline: Color,
    val isDark: Boolean,
)

val LightColors = DemoColors(
    background = Color(0xFFFAFAFB),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF111827),
    muted = Color(0xFF6B7280),
    accent = Color(0xFF6366F1),
    accentSoft = Color(0xFFEEF0FF),
    outline = Color(0xFFE5E7EB),
    isDark = false,
)

val DarkColors = DemoColors(
    background = Color(0xFF0E0F13),
    surface = Color(0xFF1B1E24),
    onSurface = Color(0xFFF3F4F6),
    muted = Color(0xFF9CA3AF),
    accent = Color(0xFF9AA2FF),
    accentSoft = Color(0xFF262A33),
    outline = Color(0xFF2C313B),
    isDark = true,
)

val LocalDemoColors = staticCompositionLocalOf { LightColors }
