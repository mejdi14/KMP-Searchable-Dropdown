package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.SearchSettings
import org.example.project.ui.SearchableDropdown
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Cyan
        ) {
            val students = listOf(
                Student("Alice", 20, "Good student"),
                Student("Bob", 22, "Needs improvement"),
                Student("Charlie", 21, "Excellent work")
            )
            Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                Spacer(modifier = Modifier.height(200.dp))
                SearchableDropdown(
                    items = students,
                    SearchSettings(
                        searchProperties = listOf(
                            Student::name,
                            Student::age,
                            Student::note
                        )
                    ),
                    itemContentConfig = ItemContentConfig.Default(DefaultDropdownItem<Student>(title = Student::name)),
                    )
                Spacer(modifier = Modifier.height(200.dp))
            }
        }
    }
}

data class Student(
    val name: String,
    val age: Int,
    val note: String
)
