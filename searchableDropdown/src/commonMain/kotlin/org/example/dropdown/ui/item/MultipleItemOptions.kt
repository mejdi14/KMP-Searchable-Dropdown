package org.example.dropdown.ui.item

import org.example.dropdown.data.enum.DefaultSelectorPosition

data class MultipleItemOptions(
    val selectionMaxCount: Int? = null,
    val useDefaultSelector: Boolean = false,
    val defaultSelectorPosition: DefaultSelectorPosition = DefaultSelectorPosition.START,
)