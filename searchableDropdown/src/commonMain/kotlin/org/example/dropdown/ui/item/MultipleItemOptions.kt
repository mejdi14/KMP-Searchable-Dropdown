package org.example.dropdown.ui.item

import org.example.dropdown.data.enum.DefaultSelectorPosition
import org.example.dropdown.data.selection.CheckboxParams

data class MultipleItemOptions(
    val selectionMaxCount: Int? = null,
    val useDefaultSelector: Boolean = false,
    val defaultSelectorPosition: DefaultSelectorPosition = DefaultSelectorPosition.START,
    val defaultCheckboxParams: CheckboxParams = CheckboxParams()
)