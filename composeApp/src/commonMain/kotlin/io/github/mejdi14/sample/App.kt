package io.github.mejdi14.sample

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mejdi14.sample.multipleDemo.MultipleAgentDemo
import io.github.mejdi14.sample.multipleDemo.MultipleCountryDemo
import io.github.mejdi14.sample.multipleDemo.MultiplePeopleDemo
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

private data class DemoPageInfo(
    val index: String,
    val title: String,
    val description: String,
    val content: @Composable () -> Unit,
)

private val demoPages: List<DemoPageInfo> = listOf(
    DemoPageInfo(
        index = "01",
        title = "Reorderable multi-select",
        description = "Search people by name or role, then long-press any result to drag it into the order you want.",
        content = { MultiplePeopleDemo() },
    ),
    DemoPageInfo(
        index = "02",
        title = "Command-palette search",
        description = "A search-first picker: type to filter, esc lives inside the field, and a keyboard-style footer sits below.",
        content = { MultipleCountryDemo() },
    ),
    DemoPageInfo(
        index = "03",
        title = "Search inside the header",
        description = "The search field is hosted in the header itself, pairing soft checkbox rows with removable chips.",
        content = { MultipleAgentDemo() },
    ),
)

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
                DemoPager(dark = dark, onToggleTheme = { dark = !dark })
            }
        }
    }
}

@Composable
private fun DemoPager(dark: Boolean, onToggleTheme: () -> Unit) {
    val pageCount = demoPages.size + 1
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
    ) {
        Masthead(dark = dark, onToggleTheme = onToggleTheme)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) { page ->
            if (page < demoPages.size) {
                DemoPage(demoPages[page], total = pageCount)
            } else {
                PlaygroundPage(
                    index = pageCount.toString().padStart(2, '0'),
                    total = pageCount,
                )
            }
        }

        PagerFooter(
            count = pageCount,
            current = pagerState.currentPage,
            onSelect = { scope.launch { pagerState.animateScrollToPage(it) } },
        )
    }
}

@Composable
private fun Masthead(dark: Boolean, onToggleTheme: () -> Unit) {
    val colors = LocalDemoColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 24.dp, top = 28.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Column {
            Text(
                text = "Searchable Dropdown",
                color = colors.onSurface,
                fontFamily = FontFamily.Serif,
                fontSize = 26.sp,
                letterSpacing = (-0.3).sp,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "COMPOSE MULTIPLATFORM · ANDROID · IOS · DESKTOP · WEB",
                color = colors.muted,
                fontSize = 10.sp,
                letterSpacing = 1.5.sp,
            )
        }
        ThemeToggle(dark = dark, onToggle = onToggleTheme)
    }
}

@Composable
private fun ThemeToggle(dark: Boolean, onToggle: () -> Unit) {
    val colors = LocalDemoColors.current
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onToggle() }
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(text = if (dark) "☾" else "☀", color = colors.muted, fontSize = 13.sp)
        Text(
            text = if (dark) "Dark" else "Light",
            color = colors.muted,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp,
        )
    }
}

@Composable
private fun DemoPage(info: DemoPageInfo, total: Int) {
    val colors = LocalDemoColors.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(modifier = Modifier.widthIn(max = 520.dp)) {
            info.content()
        }
        Spacer(Modifier.height(44.dp))
        Text(
            text = "${info.index} — ${total.toString().padStart(2, '0')}",
            color = colors.muted,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            letterSpacing = 2.sp,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = info.title,
            color = colors.onSurface,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp,
            letterSpacing = (-0.4).sp,
            lineHeight = 34.sp,
        )
        Spacer(Modifier.height(14.dp))
        Text(
            text = info.description,
            color = colors.muted,
            fontSize = 15.sp,
            lineHeight = 23.sp,
            modifier = Modifier.widthIn(max = 420.dp),
        )
    }
}

@Composable
private fun PagerFooter(count: Int, current: Int, onSelect: (Int) -> Unit) {
    val colors = LocalDemoColors.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(count) { i ->
                val active = i == current
                Box(
                    modifier = Modifier
                        .size(if (active) 7.dp else 6.dp)
                        .clip(CircleShape)
                        .background(if (active) colors.onSurface else colors.outline)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { onSelect(i) },
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Swipe to explore what the component can do.",
            color = colors.muted,
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontSize = 13.sp,
        )
    }
}
