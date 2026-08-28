package io.github.mejdi14.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mejdi14.sample.multipleDemo.MultipleAgentDemo
import io.github.mejdi14.sample.multipleDemo.MultipleCountryDemo
import io.github.mejdi14.sample.multipleDemo.MultiplePeopleDemo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var dark by remember { mutableStateOf(false) }
    val colors = if (dark) DarkColors else LightColors
    val materialColors = if (dark) {
        darkColors(
            primary = colors.accent,
            background = colors.background,
            surface = colors.surface,
            onSurface = colors.onSurface,
            onBackground = colors.onSurface,
        )
    } else {
        lightColors(
            primary = colors.accent,
            background = colors.background,
            surface = colors.surface,
            onSurface = colors.onSurface,
            onBackground = colors.onSurface,
        )
    }
    MaterialTheme(colors = materialColors) {
        CompositionLocalProvider(LocalDemoColors provides colors) {
            Surface(modifier = Modifier.fillMaxSize(), color = colors.background) {
                DemoScreen(dark = dark, onToggleTheme = { dark = !dark })
            }
        }
    }
}

@Composable
private fun DemoScreen(dark: Boolean, onToggleTheme: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding()
            .padding(bottom = 16.dp)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp)
    ) {
        Spacer(Modifier.height(56.dp))
        DemoHeader(dark = dark, onToggleTheme = onToggleTheme)
        Spacer(Modifier.height(44.dp))

        DemoSection(
            index = 1,
            title = "People picker",
            description = "Multi-select with avatars, roles and live search across name and job.",
        ) {
            MultiplePeopleDemo()
        }
        Spacer(Modifier.height(44.dp))

        DemoSection(
            index = 2,
            title = "Country code",
            description = "Flags and built-in checkbox selectors, following the current theme.",
        ) {
            MultipleCountryDemo()
        }
        Spacer(Modifier.height(44.dp))

        DemoSection(
            index = 3,
            title = "Agency team",
            description = "Colorful removable chips, plus the search field hosted in the header itself.",
        ) {
            MultipleAgentDemo()
        }
        Spacer(Modifier.height(56.dp))
    }
}

@Composable
private fun DemoHeader(dark: Boolean, onToggleTheme: () -> Unit) {
    val colors = LocalDemoColors.current
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "COMPOSE MULTIPLATFORM",
                color = colors.accent,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.5.sp,
            )
            ThemeToggle(dark = dark, onToggle = onToggleTheme)
        }
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Searchable Dropdown",
            color = colors.onSurface,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp,
            lineHeight = 38.sp,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "One configurable, searchable multi-select component — running the same code on Android, iOS, Desktop and Web.",
            color = colors.muted,
            fontSize = 15.sp,
            lineHeight = 22.sp,
        )
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(52.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Brush.horizontalGradient(listOf(colors.accent, Color(0xFFA855F7))))
        )
    }
}

@Composable
private fun ThemeToggle(dark: Boolean, onToggle: () -> Unit) {
    val colors = LocalDemoColors.current
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(colors.accentSoft)
            .border(1.dp, colors.outline, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onToggle() }
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(text = if (dark) "☾" else "☀", color = colors.accent, fontSize = 13.sp)
        Text(
            text = if (dark) "Dark" else "Light",
            color = colors.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun DemoSection(
    index: Int,
    title: String,
    description: String,
    content: @Composable () -> Unit,
) {
    val colors = LocalDemoColors.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(colors.accentSoft),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = index.toString(),
                    color = colors.accent,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = title,
                color = colors.onSurface,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.2).sp,
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            color = colors.muted,
            fontSize = 13.5.sp,
            lineHeight = 19.sp,
        )
        Spacer(Modifier.height(18.dp))
        content()
    }
}
