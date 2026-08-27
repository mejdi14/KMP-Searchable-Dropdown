package io.github.mejdi14.searchabledropdown.ui.icon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * A classic 2x3 dot "drag handle" indicator, drawn with a Canvas so it needs no drawable
 * resource. Used as the default indicator for the row being dragged during reordering.
 */
@Composable
fun DragHandleIcon(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF9E9E9E),
) {
    Canvas(modifier = modifier.size(width = 14.dp, height = 22.dp)) {
        val dotRadius = 1.6.dp.toPx()
        val columns = listOf(size.width * 0.32f, size.width * 0.68f)
        val rows = listOf(size.height * 0.22f, size.height * 0.5f, size.height * 0.78f)
        columns.forEach { x ->
            rows.forEach { y ->
                drawCircle(color = color, radius = dotRadius, center = Offset(x, y))
            }
        }
    }
}
