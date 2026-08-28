package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties

/**
 * Tap-outside-to-dismiss for the header-search popup (which is non-focusable, so it can't use the
 * built-in dismiss-on-click). Two transparent scrims are placed *around* the header — one above,
 * one below — leaving the header strip itself free so its search field and clear button stay
 * tappable. The list popup draws on top of the scrims.
 *
 * Crucially, each scrim reads the header's position **inside its position provider** (i.e. at
 * layout time, every layout pass), NOT during composition. The header moves when the keyboard
 * scrolls it, and a composition-time read lags behind — which used to leave the "above" scrim
 * covering the header and swallowing taps meant for the clear button.
 */
@Composable
internal fun HeaderSearchDismissScrims(
    headerCoordinates: LayoutCoordinates?,
    onDismiss: () -> Unit,
) {
    if (headerCoordinates == null) return
    ScrimPopup(headerCoordinates, above = true, onDismiss = onDismiss)
    ScrimPopup(headerCoordinates, above = false, onDismiss = onDismiss)
}

@Composable
private fun ScrimPopup(headerCoordinates: LayoutCoordinates, above: Boolean, onDismiss: () -> Unit) {
    val density = LocalDensity.current
    val marginPx = with(density) { 8.dp.roundToPx() }
    // Size is produced by the provider (which has the window size + live header position) and read
    // back here for the Box. One-frame lag on the (invisible) size is harmless.
    val sizeState = remember { mutableStateOf(IntSize.Zero) }

    val positionProvider = remember(headerCoordinates, above, marginPx) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
            ): IntOffset {
                val top = headerCoordinates.positionInWindow().y.toInt()
                val bottom = top + headerCoordinates.size.height
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
