package org.example.project.demo

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.data.selection.SingleItemContentConfig
import org.example.project.data.Student
import org.example.project.data.students
import org.example.project.ui.SearchableDropdown

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