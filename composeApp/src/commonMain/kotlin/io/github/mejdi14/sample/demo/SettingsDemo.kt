package io.github.mejdi14.sample.demo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.SingleItemContentConfig
import io.github.mejdi14.sample.data.settings
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown

@Composable
fun SettingsDemo() {
    SearchableDropdown(
        items = settings,
        searchSettings = SearchSettings(
            searchEnabled = false
        ),
        dropdownConfig = DropdownConfig(shape = RoundedCornerShape(8.dp), headerPlaceholder = {
            Text("App Settings",
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 16.dp))
        }),
        itemContentConfig = SingleItemContentConfig.Custom(
            content = { setting, selectedSetting ->

                setting.content()
            }
        ),
    )
}