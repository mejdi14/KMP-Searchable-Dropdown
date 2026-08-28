package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties

@Composable
internal fun HeaderSearchDismissScrims(
    anchorBounds: IntRect,
    onDismiss: () -> Unit,
) {
    if (anchorBounds == IntRect.Zero) return
    ScrimPopup(anchorBounds, above = true, onDismiss = onDismiss)
    ScrimPopup(anchorBounds, above = false, onDismiss = onDismiss)
}

@Composable
private fun ScrimPopup(anchorBounds: IntRect, above: Boolean, onDismiss: () -> Unit) {
    val density = LocalDensity.current
    val marginPx = with(density) { 8.dp.roundToPx() }

    val sizeState = remember { mutableStateOf(IntSize.Zero) }

    val positionProvider = remember(anchorBounds, above, marginPx) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
            ): IntOffset {
                val top = anchorBounds.top
                val bottom = anchorBounds.bottom
                val offset: IntOffset
                val size: IntSize
                if (above) {
                    offset = IntOffset(0, 0)
                    size = IntSize(windowSize.width, (top - marginPx).coerceAtLeast(0))
                } else {
                    val y = bottom + marginPx
                    offset = IntOffset(0, y)
                    size = IntSize(windowSize.width, (windowSize.height - y).coerceAtLeast(0))
                }
                if (sizeState.value != size) sizeState.value = size
                return offset
            }
        }
    }

    Popup(
        popupPositionProvider = positionProvider,
        properties = PopupProperties(focusable = false, clippingEnabled = false),
    ) {
        val s = sizeState.value
        Box(
            Modifier
                .size(
                    width = with(density) { s.width.toDp() },
                    height = with(density) { s.height.toDp() },
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { onDismiss() }
        )
    }
}
