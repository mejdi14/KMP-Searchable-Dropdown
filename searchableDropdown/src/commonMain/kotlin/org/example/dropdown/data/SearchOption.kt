package org.example.dropdown.data

import androidx.compose.runtime.Composable
import org.example.dropdown.ui.SearchSeparator
import kotlin.reflect.KProperty1


data class SearchSettings<T : Any>(
    val searchProperties: List<KProperty1<T, *>> = emptyList(),
    val separator : @Composable () -> Unit = { SearchSeparator() }
)