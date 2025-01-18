package org.example.project.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.project.data.people
import org.example.project.data.settings
import org.example.project.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource

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
        itemContentConfig = ItemContentConfig.Custom(
            content = { setting, selectedSetting ->

                setting.content()
            }
        ),
    )
}