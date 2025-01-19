package org.example.project.demo

import androidx.compose.runtime.Composable
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.project.data.Student
import org.example.project.data.students
import org.example.project.ui.SearchableDropdown

@Composable
fun StudentDemo (){
    SearchableDropdown(
        items = students,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                Student::name,
                Student::age,
                Student::note
            )
        ),
        itemContentConfig = ItemContentConfig.Default(DefaultDropdownItem<Student>(title = Student::name)),
    )
}