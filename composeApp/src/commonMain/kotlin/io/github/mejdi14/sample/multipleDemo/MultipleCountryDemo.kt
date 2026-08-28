package io.github.mejdi14.sample.multipleDemo

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
import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.expand_less
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.ToggleIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchInput
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.searchabledropdown.ui.item.DefaultItemHeaderComposable
import io.github.mejdi14.searchabledropdown.ui.item.MultipleItemOptions
import io.github.mejdi14.sample.data.Country
import io.github.mejdi14.sample.data.countries
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun MultipleCountryDemo() {
    SearchableDropdown(
        items = countries,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                Country::name,
                Country::phoneCode,
            ),
            searchIcon = SearchIcon(iconTintColor = Color.White),
            searchInput = SearchInput(
                placeholder = { Text("Searching...", color = Color.White) },
                inputTextColor = Color.White
            )
        ),
        dropdownConfig = DropdownConfig(
            shape = RoundedCornerShape(0.dp), headerBackgroundColor = Color(0xFF282b34),
            contentBackgroundColor = Color(0xFF1c1f24),
            headerPlaceholder = {
                Text(
                    "Select Country Code", color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }, toggleIcon = ToggleIcon(Res.drawable.expand_less, iconTintColor = Color.White),
            separationSpace = 0

        ),
        itemContentConfig = MultipleItemContentConfig.Custom(
            content = { country, isSelected, multipleSelectActionListener ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(country.flagResources),
                        modifier = Modifier.size(32.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = country.name,
                        color = Color.White
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "",
                        color = Color.Gray
                    )

                }
            },
            header = { country, selectedCountry, multipleRemoveItemListener ->
                DefaultItemHeaderComposable(
                    item = country,
                    title = Country::name,
                    removeItemListener = multipleRemoveItemListener
                )
            },
            options = MultipleItemOptions(useDefaultSelector = true)

        ),
    )
}
