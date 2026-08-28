package io.github.mejdi14.searchabledropdown.ui.icon

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

@Composable
fun RoundInitialsIcon(
    fullName: String,
    modifier: Modifier = Modifier,
    size: Dp = 28.dp
) {

    val backgroundColor = remember(fullName) {
        val hash = fullName.hashCode()
        Color(
            red = (hash and 0xFF),
            green = (hash shr 8 and 0xFF),
            blue = (hash shr 16 and 0xFF)
        )
    }

    val initials = remember(fullName) {
        val words = fullName.split(" ").filter { it.isNotBlank() }
        if (words.size >= 2) {
            "${words[0].firstOrNull()?.uppercase() ?: ""}${words[1].firstOrNull()?.uppercase() ?: ""}"
        } else {

            words.firstOrNull()?.take(1)?.uppercase() ?: "?"
        }
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
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
