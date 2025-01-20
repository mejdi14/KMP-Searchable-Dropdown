package org.example.project.multipleDemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.green_check
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.data.selection.MultipleItemContentConfig
import org.example.dropdown.data.selection.SingleItemContentConfig
import org.example.project.data.People
import org.example.project.data.people
import org.example.project.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun MultiplePeopleDemo() {
    SearchableDropdown(
        items = people,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                People::name,
                People::job,
            )
        ),
        dropdownConfig = DropdownConfig(shape = RoundedCornerShape(18.dp),
            headerPlaceholder = {
                Text(
                    "Your favorite person", color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }),
        itemContentConfig = MultipleItemContentConfig.Custom(
            content = { person, selectedPerson, multipleSelectActionListener ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(person.photo),
                        modifier = Modifier.size(32.dp)
                            .clickable{
                                multipleSelectActionListener.onSelect(person)
                            },
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = person.name,
                        )
                        Text(
                            text = person.job,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    if (person == selectedPerson)
                        Image(
                            painterResource(Res.drawable.green_check),
                            modifier = Modifier.size(22.dp),
                            contentDescription = "",
                        )

                }

            },
            header = { person, selectedPerson, removeItemListener ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(person.photo),
                        modifier = Modifier.size(32.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = person.name,
                    )

                    Spacer(Modifier.width(12.dp))
                    if (person == selectedPerson)
                        Image(
                            painterResource(Res.drawable.green_check),
                            modifier = Modifier.size(22.dp),
                            contentDescription = "",
                        )

                }
            }
        ),
    )
}