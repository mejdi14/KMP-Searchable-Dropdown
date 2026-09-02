package io.github.mejdi14.sample

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.DropdownShadow
import io.github.mejdi14.searchabledropdown.data.PopupPlacement
import io.github.mejdi14.searchabledropdown.data.ToggleIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchInput
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown
import io.github.mejdi14.sample.data.Country
import io.github.mejdi14.sample.data.countries
import org.jetbrains.compose.resources.painterResource

@Composable
fun PlaygroundPage(index: String, total: Int) {
    val colors = LocalDemoColors.current
    var placement by remember { mutableStateOf(PopupPlacement.AUTO) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp),
    ) {
        Spacer(Modifier.height(28.dp))
        Text(
            text = "$index — ${total.toString().padStart(2, '0')}",
            color = colors.muted,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            letterSpacing = 2.sp,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Auto-scroll placement",
            color = colors.onSurface,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp,
            letterSpacing = (-0.4).sp,
            lineHeight = 34.sp,
        )
        Spacer(Modifier.height(14.dp))
        Text(
            text = "Scroll the field into the middle, then open it. The list scrolls the header to an edge so it always has room — pick who decides.",
            color = colors.muted,
            fontSize = 15.sp,
            lineHeight = 23.sp,
            modifier = Modifier.widthIn(max = 440.dp),
        )
        Spacer(Modifier.height(22.dp))
        PlacementControl(selected = placement, onSelect = { placement = it })

        Spacer(Modifier.height(360.dp))
        PlaygroundDropdown(placement)
        Spacer(Modifier.height(520.dp))

        Text(
            text = "End of the playground — scroll back up.",
            color = colors.muted,
            fontFamily = FontFamily.Serif,
            fontSize = 13.sp,
        )
        Spacer(Modifier.height(48.dp))
    }
}

@Composable
private fun PlacementControl(selected: PopupPlacement, onSelect: (PopupPlacement) -> Unit) {
    val colors = LocalDemoColors.current
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        PopupPlacement.entries.forEach { option ->
            val active = option == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (active) colors.onSurface else Color.Transparent)
                    .border(
                        width = 1.dp,
                        color = if (active) colors.onSurface else colors.outline,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { onSelect(option) }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = option.name,
                    color = if (active) colors.surface else colors.muted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp,
                )
            }
        }
    }
}

@Composable
private fun PlaygroundDropdown(placement: PopupPlacement) {
    val c = LocalDemoColors.current
    val shape = RoundedCornerShape(16.dp)
    SearchableDropdown(
        items = countries,
        searchSettings = SearchSettings(
            searchProperties = listOf(Country::name, Country::iso),
            searchIcon = SearchIcon(iconTintColor = c.muted),
            searchInput = SearchInput(
                placeholder = { Text("Search country…", color = c.muted) },
                inputTextColor = c.onSurface,
            ),
        ),
        dropdownConfig = DropdownConfig(
            shape = shape,
            headerBackgroundColor = c.surface,
            contentBackgroundColor = c.surface,
            dropdownShadow = DropdownShadow(elevation = 8.dp, shape = shape),
            horizontalPadding = 18.dp,
            separationSpace = 12,
            popupPlacement = placement,
            toggleIcon = ToggleIcon(iconTintColor = c.muted),
            headerPlaceholder = {
                Text(
                    "Open me anywhere",
                    color = c.muted,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 16.dp),
                )
            },
        ),
        itemContentConfig = MultipleItemContentConfig.Custom(
            content = { country, isSelected, listener ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            if (isSelected) listener.onDeselect(country) else listener.onSelect(country)
                        }
                        .padding(vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource(country.flagResources),
                        modifier = Modifier.size(24.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(14.dp))
                    Text(
                        text = country.name,
                        color = c.onSurface,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f),
                    )
                    if (isSelected) {
                        Text("✓", color = c.accent, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            },
            header = { country, _, removeItemListener ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(c.accentSoft)
                        .padding(start = 8.dp, end = 6.dp, top = 3.dp, bottom = 3.dp),
                ) {
                    Image(
                        painterResource(country.flagResources),
                        modifier = Modifier.size(18.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(country.iso, color = c.onSurface, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "×",
                        color = c.muted,
                        fontSize = 16.sp,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { removeItemListener.onRemove(country) },
                    )
                }
            },
        ),
    )
}
