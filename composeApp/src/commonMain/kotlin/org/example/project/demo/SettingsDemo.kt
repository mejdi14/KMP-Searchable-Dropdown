package org.example.project.demo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.data.selection.SingleItemContentConfig
import org.example.project.data.settings
import org.example.project.ui.SearchableDropdown

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