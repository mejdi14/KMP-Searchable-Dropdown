package org.example.project.data

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.Checkbox
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.archive
import kmp_searchable_dropdown.composeapp.generated.resources.arrow_horizontal
import kmp_searchable_dropdown.composeapp.generated.resources.bell
import kmp_searchable_dropdown.composeapp.generated.resources.bolt
import kmp_searchable_dropdown.composeapp.generated.resources.box
import kmp_searchable_dropdown.composeapp.generated.resources.expand_less
import kmp_searchable_dropdown.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.painterResource


data class Settings(
    val label: String,
    val content: @Composable () -> Unit,
)

val settings = listOf<Settings>(
    Settings("Theme", {
        SimpleSetting()
    }),
    Settings("Package", {
        PackageSetting()
    }),
    Settings("Notifications", { ExpandableSettingsRow() }),
    Settings("Animation", { SwitcherSetting() }),
    Settings("Archive", { ArchiveSetting() })
)

@Composable
private fun SimpleSetting() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(Res.drawable.settings),
            modifier = Modifier.size(22.dp),
            contentDescription = "",
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Settings",
            Modifier.weight(1f)
        )
        Spacer(Modifier.width(12.dp))
        Image(
            painterResource(Res.drawable.expand_less),
            modifier = Modifier.size(18.dp)
                .rotate(90f),
            contentDescription = "",
        )

    }
}

@Composable
private fun PackageSetting() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(Res.drawable.box),
            modifier = Modifier.size(22.dp),
            contentDescription = "",
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Package",
            Modifier.weight(1f)
        )
        Spacer(Modifier.width(12.dp))
        Image(
            painterResource(Res.drawable.expand_less),
            modifier = Modifier.size(18.dp)
                .rotate(90f),
            contentDescription = "",
        )

    }
}

@Composable
private fun ArchiveSetting() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(Res.drawable.archive),
            modifier = Modifier.size(22.dp),
            contentDescription = "",
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Delete Account",
            Modifier.weight(1f),
            color = Color.Red
        )
        Spacer(Modifier.width(12.dp))


    }
}

@Composable
private fun SwitcherSetting() {
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(Res.drawable.bolt),
            modifier = Modifier.size(22.dp),
            contentDescription = "",
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Activate Animation",
            Modifier.weight(1f)
        )
        Spacer(Modifier.width(12.dp))
        Switch(
            checked = checked,
            onCheckedChange = { checked = it }
        )

    }
}

@Composable
fun SimpleSwitch() {
    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked,
        onCheckedChange = { checked = it },
        // Optional modifiers and colors
        modifier = Modifier.padding(8.dp),

    )
}


@Composable
fun ExpandableSettingsRow() {
    var isExpanded by remember { mutableStateOf(false) }
    var option1Checked by remember { mutableStateOf(false) }
    var option2Checked by remember { mutableStateOf(false) }
    var option3Checked by remember { mutableStateOf(false) }

    Column {
        // Main Settings Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { isExpanded = !isExpanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(Res.drawable.bell),
                modifier = Modifier.size(22.dp),
                contentDescription = "Settings Icon",
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = "Notification",
                modifier = Modifier.weight(1f)
            )
            Image(
                painterResource(Res.drawable.arrow_horizontal),
                modifier = Modifier
                    .size(22.dp)
                    .rotate( 90f),
                contentDescription = "Expand Arrow",
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier.padding(start = 60.dp, end = 16.dp)
            ) {
                SettingsOption(
                    text = "Day",
                    checked = option1Checked,
                    onCheckedChange = { option1Checked = it }
                )

                SettingsOption(
                    text = "Evening",
                    checked = option2Checked,
                    onCheckedChange = { option2Checked = it }
                )

                SettingsOption(
                    text = "Night",
                    checked = option3Checked,
                    onCheckedChange = { option3Checked = it }
                )
            }
        }
    }
}

@Composable
private fun SettingsOption(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

