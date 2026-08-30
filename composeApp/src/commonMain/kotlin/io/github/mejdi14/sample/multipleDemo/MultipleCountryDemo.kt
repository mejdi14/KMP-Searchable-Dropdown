package io.github.mejdi14.sample.multipleDemo

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.expand_less
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.DropdownShadow
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchInput
import io.github.mejdi14.searchabledropdown.data.search.SearchLocation
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.sample.DemoColors
import io.github.mejdi14.sample.LocalDemoColors
import io.github.mejdi14.sample.data.Country
import io.github.mejdi14.sample.data.countries
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun MultipleCountryDemo() {
    val c = LocalDemoColors.current
    val paletteShape = RoundedCornerShape(18.dp)
    SearchableDropdown(
        items = countries,
        searchSettings = SearchSettings(
            searchProperties = listOf(Country::name, Country::iso, Country::phoneCode),
            searchIcon = SearchIcon(iconTintColor = c.muted),
            searchInput = SearchInput(
                placeholder = {
                    Text("Search ${countries.size} countries", color = c.muted, fontSize = 16.sp)
                },
                inputTextColor = c.onSurface,
            ),
            searchLocation = SearchLocation.HEADER,
        ),
        dropdownConfig = DropdownConfig(
            shape = paletteShape,
            headerBackgroundColor = c.surface,
            contentBackgroundColor = c.surface,
            dropdownShadow = DropdownShadow(elevation = 18.dp, shape = paletteShape),
            horizontalPadding = 16.dp,
            separationSpace = 14,
            headerPlaceholder = {
                Text(
                    "Select country",
                    color = c.muted,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 16.dp),
                )
            },
            headerTrailing = { expanded, onToggle ->
                if (expanded) {
                    Box(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { onToggle() },
                    ) {
                        Keycap("esc", c)
                    }
                } else {
                    Icon(
                        painter = painterResource(Res.drawable.expand_less),
                        contentDescription = "Open",
                        tint = c.muted,
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(180f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { onToggle() },
                    )
                }
            },
            footer = {
                Column(Modifier.fillMaxWidth()) {
                    Box(Modifier.fillMaxWidth().height(1.dp).background(c.outline))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 11.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                    ) {
                        Keycap("↑", c)
                        Keycap("↓", c)
                        Text("move", color = c.muted, fontSize = 12.5.sp)
                        Spacer(Modifier.width(6.dp))
                        Keycap("↵", c)
                        Text("select", color = c.muted, fontSize = 12.5.sp)
                        Spacer(Modifier.weight(1f))
                        Text(
                            "${countries.size} countries",
                            color = c.muted,
                            fontSize = 12.5.sp,
                        )
                    }
                }
            },
        ),
        itemContentConfig = MultipleItemContentConfig.Custom(
            content = { country, isSelected, listener ->
                val isFocused = country == countries.first()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .then(
                            if (isFocused) Modifier
                                .shadow(2.dp, RoundedCornerShape(12.dp))
                                .background(c.surface, RoundedCornerShape(12.dp))
                                .border(1.dp, c.outline, RoundedCornerShape(12.dp))
                            else Modifier
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            if (isSelected) listener.onDeselect(country) else listener.onSelect(country)
                        }
                        .padding(horizontal = 10.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource(country.flagResources),
                        modifier = Modifier.size(26.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(14.dp))
                    Text(
                        text = country.name,
                        color = c.onSurface,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                    )
                    IsoPill(country.iso, isSelected, c)
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

@Composable
private fun Keycap(label: String, c: DemoColors) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(c.background)
            .border(1.dp, c.outline, RoundedCornerShape(6.dp))
            .padding(horizontal = 7.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, color = c.muted, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun IsoPill(iso: String, selected: Boolean, c: DemoColors) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(7.dp))
            .background(if (selected) c.accentSoft else c.background)
            .border(1.dp, c.outline, RoundedCornerShape(7.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            iso,
            color = if (selected) c.accent else c.muted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
        )
    }
}
