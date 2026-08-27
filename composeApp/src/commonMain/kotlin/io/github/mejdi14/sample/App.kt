package io.github.mejdi14.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

private val Background = Color(0xFFFAFAFB)
private val Ink = Color(0xFF111827)
private val Muted = Color(0xFF6B7280)
private val Accent = Color(0xFF6366F1)
private val AccentSoft = Color(0xFFEEF0FF)

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            DemoScreen()
        }
    }
}

@Composable
private fun DemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            // Keep content clear of the system bars (we draw edge-to-edge) and, crucially, above
            // the keyboard so the in-header search field scrolls into view when the IME opens.
            .systemBarsPadding()
            .imePadding()
            // Small gap so the header/search field never sits flush against the keyboard.
            .padding(bottom = 16.dp)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp)
    ) {
        Spacer(Modifier.height(56.dp))
        DemoHeader()
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
            description = "A dark, borderless theme with flags and built-in checkbox selectors.",
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
private fun DemoHeader() {
    Column {
        Text(
            text = "COMPOSE MULTIPLATFORM",
            color = Accent,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.5.sp,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Searchable Dropdown",
            color = Ink,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp,
            lineHeight = 38.sp,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "One configurable, searchable multi-select component — running the same code on Android, iOS, Desktop and Web.",
            color = Muted,
            fontSize = 15.sp,
            lineHeight = 22.sp,
        )
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(52.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(
                    Brush.horizontalGradient(listOf(Accent, Color(0xFFA855F7)))
                )
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
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(AccentSoft),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = index.toString(),
                    color = Accent,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = title,
                color = Ink,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.2).sp,
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            color = Muted,
            fontSize = 13.5.sp,
            lineHeight = 19.sp,
        )
        Spacer(Modifier.height(18.dp))
        content()
    }
}
