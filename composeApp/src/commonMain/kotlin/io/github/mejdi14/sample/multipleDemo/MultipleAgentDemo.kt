package io.github.mejdi14.sample.multipleDemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.ToggleIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchInput
import io.github.mejdi14.searchabledropdown.data.search.SearchLocation
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.sample.DemoColors
import io.github.mejdi14.sample.LocalDemoColors
import io.github.mejdi14.sample.data.Agent
import io.github.mejdi14.sample.data.agents
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun MultipleAgentDemo() {
    val c = LocalDemoColors.current
    SearchableDropdown(
        items = agents,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                Agent::name,
            ),
            searchIcon = SearchIcon(iconTintColor = c.muted),
            searchInput = SearchInput(
                placeholder = { Text("Search team…", color = c.muted) },
                inputTextColor = c.onSurface,
            ),
            searchLocation = SearchLocation.HEADER,
        ),
        dropdownConfig = DropdownConfig(shape = RoundedCornerShape(18.dp),
            horizontalPadding = 12.dp,
            headerBackgroundColor = c.surface,
            contentBackgroundColor = c.surface,
            toggleIcon = ToggleIcon(iconTintColor = c.muted),
            separationSpace = 45,
            headerPlaceholder = {
                Text(
                    "Agency Team", color = c.onSurface,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }),
        itemContentConfig = MultipleItemContentConfig.Custom(
            content = { agent, isSelected, multipleSelectActionListener ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) c.accentSoft else c.background)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            if (isSelected)
                                multipleSelectActionListener.onDeselect(agent)
                            else
                                multipleSelectActionListener.onSelect(agent)
                        }
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SoftCheckbox(isSelected, c)
                    Spacer(Modifier.width(16.dp))
                    Image(
                        painterResource(agent.photo),
                        modifier = Modifier.size(32.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = agent.name,
                        color = c.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                    )
                }
            },
            header = { agent, selectedPerson, removeItemListener ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(c.accentSoft)
                        .padding(start = 8.dp, end = 6.dp, top = 3.dp, bottom = 3.dp),
                ) {
                    Image(
                        painterResource(agent.photo),
                        modifier = Modifier.size(18.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(agent.name, color = c.onSurface, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "×",
                        color = c.muted,
                        fontSize = 16.sp,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { removeItemListener.onRemove(agent) },
                    )
                }
            },
        ),
    )
}

@Composable
private fun SoftCheckbox(checked: Boolean, c: DemoColors) {
    Box(
        modifier = Modifier
            .size(22.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(if (checked) c.accent else Color.Transparent)
            .border(
                width = 1.5.dp,
                color = if (checked) c.accent else c.outline,
                shape = RoundedCornerShape(7.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            Text("✓", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}
