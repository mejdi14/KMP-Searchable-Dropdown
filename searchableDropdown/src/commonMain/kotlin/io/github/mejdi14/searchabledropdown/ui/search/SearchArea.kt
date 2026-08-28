package io.github.mejdi14.searchabledropdown.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun <T : Any> SearchArea(
    searchQuery: MutableState<String>,
    searchSettings: SearchSettings<T>,
    showInlineClear: Boolean = true,
) {
    val searchInput = searchSettings.searchInput
    val textStyle = LocalTextStyle.current.copy(color = searchInput.inputTextColor)
    BasicTextField(
        value = searchQuery.value,
        onValueChange = {
            searchSettings.searchActionListener.onSearchTextWatcher(it)
            searchQuery.value = it
        },
        singleLine = true,
        textStyle = textStyle,
        cursorBrush = SolidColor(searchInput.inputTextColor),
        keyboardOptions = searchInput.keyboardOptions,
        modifier = Modifier
            .fillMaxWidth()
            .background(searchInput.backgroundColor)
            .then(searchInput.modifier),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SearchIconComposable(searchSettings.searchIcon)
                Spacer(Modifier.width(10.dp))
                Box(Modifier.weight(1f)) {
                    if (searchQuery.value.isEmpty()) searchInput.placeholder()
                    innerTextField()
                }
                if (showInlineClear && searchQuery.value.isNotEmpty()) {
                    Spacer(Modifier.width(6.dp))
                    ClearSearchButton(searchSettings.clearSearchIcon) { searchQuery.value = "" }
                }
            }
        },
    )
}

@Composable
internal fun ClearSearchButton(icon: SearchIcon, onClear: () -> Unit) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onClear() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(icon.iconDrawable),
            contentDescription = "Clear search",
            tint = icon.iconTintColor,
            modifier = Modifier.size(18.dp),
        )
    }
}
