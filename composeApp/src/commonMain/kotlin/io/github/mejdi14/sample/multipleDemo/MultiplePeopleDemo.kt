package io.github.mejdi14.sample.multipleDemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.green_check
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.ToggleIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchInput
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.sample.LocalDemoColors
import io.github.mejdi14.sample.data.People
import io.github.mejdi14.sample.data.people
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun MultiplePeopleDemo() {
    val c = LocalDemoColors.current
    SearchableDropdown(
        items = people,
        searchSettings = SearchSettings(
            searchProperties = listOf(People::name, People::job),
            searchIcon = SearchIcon(iconTintColor = c.muted),
            searchInput = SearchInput(
                placeholder = { Text("Search people…", color = c.muted) },
                inputTextColor = c.onSurface,
            ),
        ),
        dropdownConfig = DropdownConfig(
            shape = RoundedCornerShape(18.dp),
            headerBackgroundColor = c.surface,
            contentBackgroundColor = c.surface,
            toggleIcon = ToggleIcon(iconTintColor = c.muted),
            reorderEnabled = true,
            reorderKey = { it.name },
            headerPlaceholder = {
                Text(
                    "Your favorite person",
                    color = c.onSurface,
                    modifier = Modifier.padding(vertical = 16.dp),
                )
            },
        ),
        itemContentConfig = MultipleItemContentConfig.Custom(
            content = { person, isSelected, listener ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            if (isSelected) listener.onDeselect(person) else listener.onSelect(person)
                        }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource(person.photo),
                        modifier = Modifier.size(32.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = person.name, color = c.onSurface)
                        Text(text = person.job, color = c.muted, fontSize = 12.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    if (isSelected) {
                        Image(
                            painterResource(Res.drawable.green_check),
                            modifier = Modifier.size(22.dp),
                            contentDescription = "",
                        )
                    }
                }
            },
            header = { person, _, _ ->
                Image(
                    painterResource(person.photo),
                    modifier = Modifier.size(32.dp),
                    contentDescription = "",
                )
            },
        ),
    )
}
