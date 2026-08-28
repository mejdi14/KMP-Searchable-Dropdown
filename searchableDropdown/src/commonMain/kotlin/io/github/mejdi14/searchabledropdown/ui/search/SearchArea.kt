package io.github.mejdi14.searchabledropdown.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import org.jetbrains.compose.resources.painterResource

/**
 * The search field. The leading search icon and the trailing clear button live in the text field's
 * own icon slots, so it reads like a proper search bar. The clear button only appears once there is
 * text; [showInlineClear] hides it when the clear action is hosted elsewhere (e.g. the header).
 */
@Composable
internal fun <T : Any> SearchArea(
    searchQuery: MutableState<String>,
    searchSettings: SearchSettings<T>,
    showInlineClear: Boolean = true,
) {
    val searchInput = searchSettings.searchInput
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().then(searchInput.modifier),
        value = searchQuery.value,
        onValueChange = {
            searchSettings.searchActionListener.onSearchTextWatcher(it)
            searchQuery.value = it
        },
        singleLine = true,
        placeholder = searchInput.placeholder,
        leadingIcon = { SearchIconComposable(searchSettings.searchIcon) },
        trailingIcon = if (showInlineClear && searchQuery.value.isNotEmpty()) {
            { ClearSearchButton(searchSettings.clearSearchIcon) { searchQuery.value = "" } }
        } else {
            null
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = searchInput.backgroundColor,
            focusedIndicatorColor = searchInput.focusedIndicatorColor,
            unfocusedIndicatorColor = searchInput.unfocusedIndicatorColor,
            disabledIndicatorColor = searchInput.disabledIndicatorColor,
            textColor = searchInput.inputTextColor,
        ),
        keyboardOptions = searchInput.keyboardOptions,
    )
}

/** A round, tappable clear ("x") button used to wipe the whole query. */
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
