package org.example.dropdown.ui.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.cross_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun DeleteSelectionIcon () {
    Image(painterResource(Res.drawable.cross_icon), contentDescription = "")
}