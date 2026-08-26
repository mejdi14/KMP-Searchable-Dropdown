package io.github.mejdi14.sample.demo

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.mejdi14.searchabledropdown.data.DefaultDropdownItem
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.SingleItemContentConfig
import io.github.mejdi14.sample.data.Student
import io.github.mejdi14.sample.data.students
import io.github.mejdi14.searchabledropdown.ui.SearchableDropdown

@Composable
fun StudentDemo() {
    SearchableDropdown(
        items = students,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                Student::name,
                Student::age,
                Student::note
            )
        ),
        dropdownConfig = DropdownConfig(headerPlaceholder = { Text("Select student", color = Color.Black,
            modifier = Modifier
                .padding(vertical = 16.dp)) }),
        itemContentConfig = SingleItemContentConfig.Default(
            DefaultDropdownItem<Student>(
                title = Student::name,
                subtitle = Student::note
            )
        ),
    )
}