package org.example.dropdown.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun RoundInitialsIcon(
    fullName: String,
    modifier: Modifier = Modifier,
    size: Dp = 28.dp
) {

    val randomColor = remember {
        val random = Random
        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)
        Color(red, green, blue)
    }


    val initials = remember(fullName) {
        val words = fullName.split(" ").filter { it.isNotBlank() }
        if (words.size >= 2) {
            "${words[0].firstOrNull()?.uppercase() ?: ""}${words[1].firstOrNull()?.uppercase() ?: ""}"
        } else {
            // Fallback if only one word or none
            words.firstOrNull()?.take(1)?.uppercase() ?: "?"
        }
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(randomColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            style = MaterialTheme.typography.body1.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}
