package io.github.mejdi14.sample

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.mejdi14.sample.data.Student

@Composable
 fun DropdownItem(student: Student) {
    Text(
        text = student.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = Color.Black
    )
}