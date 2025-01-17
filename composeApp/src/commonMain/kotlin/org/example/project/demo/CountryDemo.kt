package org.example.project.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.project.data.Country
import org.example.project.data.Student
import org.example.project.data.countries
import org.example.project.data.students
import org.example.project.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun CountryDemo (){
    SearchableDropdown(
        items = countries,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                Country::name,
                Country::phoneCode,
            )
        ),
        itemContentConfig = ItemContentConfig.Custom(
            content = { country ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)  // Increased height to accommodate content
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
                            )
                        }

            }
        ),
    )
}