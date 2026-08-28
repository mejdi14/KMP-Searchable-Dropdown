package io.github.mejdi14.searchabledropdown.ui.item

import io.github.mejdi14.searchabledropdown.data.enum.DefaultSelectorPosition
import io.github.mejdi14.searchabledropdown.data.selection.CheckboxParams

data class MultipleItemOptions(
    val selectionMaxCount: Int? = null,
    val useDefaultSelector: Boolean = false,
    val defaultSelectorPosition: DefaultSelectorPosition = DefaultSelectorPosition.START,
    val defaultCheckboxParams: CheckboxParams = CheckboxParams()
)
