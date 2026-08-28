package io.github.mejdi14.sample.multipleDemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.cross_icon
import kmp_searchable_dropdown.composeapp.generated.resources.green_check
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.search.SearchLocation
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.CheckboxParams
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.searchabledropdown.ui.item.MultipleItemOptions
import io.github.mejdi14.sample.data.Agent
import io.github.mejdi14.sample.data.People
import io.github.mejdi14.sample.data.agents
import io.github.mejdi14.sample.data.people
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown
import org.jetbrains.compose.resources.painterResource


@Composable
fun MultipleAgentDemo() {
    SearchableDropdown(
        items = agents,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                Agent::name,
            ),
            // Show the search field in the header itself while the popup is open.
            searchLocation = SearchLocation.HEADER,
        ),
        dropdownConfig = DropdownConfig(shape = RoundedCornerShape(18.dp),
            horizontalPadding = 12.dp,
            // A clearly visible gap between the header and the popup (this is the "defined space").
            separationSpace = 45,
            headerPlaceholder = {
                Text(
                    "Agency Team", color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }),
        itemContentConfig = MultipleItemContentConfig.Custom(
            content = { agent, isSelected, multipleSelectActionListener ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isSelected)
                                multipleSelectActionListener.onDeselect(agent)
                            else
                                multipleSelectActionListener.onSelect(agent)
                        }
                        .height(58.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(agent.photo),
                        modifier = Modifier.size(32.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = agent.name,
                        )

                    }
                    Spacer(Modifier.width(12.dp))


                }

            },
            header = { agent, selectedPerson, removeItemListener ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.background(
                        color = agent.backgroundColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Image(
                        painterResource(agent.photo),
                        modifier = Modifier.size(22.dp),
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(agent.name, color = agent.textColor)
                    Spacer(Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(Res.drawable.cross_icon), contentDescription = "",
                        tint = agent.textColor,
                        modifier = Modifier.padding(2.dp)
                            .clickable {
                                removeItemListener.onRemove(agent)
                            }
                    )
                }
            },
            options = MultipleItemOptions(
                useDefaultSelector = true,
                defaultCheckboxParams = CheckboxParams(
                    checkedColor = Color.Black,
                    checkmarkColor = Color.White
                )
            )
        ),
    )
}