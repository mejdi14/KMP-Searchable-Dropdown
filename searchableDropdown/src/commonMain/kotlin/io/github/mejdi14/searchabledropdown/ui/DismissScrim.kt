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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties

/**
 * Provides tap-outside-to-dismiss for the header-search popup, which must be non-focusable (so the
 * header search field stays interactive) and therefore can't use the built-in dismiss-on-click.
 *
 * A single full-screen scrim would cover the header and block the field, so instead two scrims are
 * placed *around* the header — one above it, one below — leaving the header strip itself free. The
 * list popup is drawn on top of these, so taps land as expected:
 * - on the header (search field) or the list -> nothing closes;
 * - anywhere else (the scrims) -> [onDismiss].
 */
@Composable
internal fun HeaderSearchDismissScrims(
    headerCoordinates: LayoutCoordinates?,
    onDismiss: () -> Unit,
) {
    if (headerCoordinates == null) return
    val headerTop = headerCoordinates.positionInWindow().y.toInt()
    val headerBottom = headerTop + headerCoordinates.size.height

    // Above the header.
    if (headerTop > 0) {
        ScrimPopup(y = 0, fixedHeightPx = headerTop, onDismiss = onDismiss)
    }
    // Below the header, down to the bottom of the window.
    ScrimPopup(y = headerBottom, fixedHeightPx = null, onDismiss = onDismiss)
}

/**
 * A transparent, non-focusable popup that covers the window width starting at [y].
 *
 * [fixedHeightPx] sets an explicit height; null means "extend to the bottom of the window". The
 * window size is learned from the position provider, so the scrim is sized exactly rather than
 * being made arbitrarily large (an oversized popup window risks exceeding platform surface limits).
 */
@Composable
private fun ScrimPopup(y: Int, fixedHeightPx: Int?, onDismiss: () -> Unit) {
    val density = LocalDensity.current
    val windowSize = remember { mutableStateOf(IntSize.Zero) }
    val positionProvider = remember(y) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize1: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
            ): IntOffset {
                if (windowSize.value != windowSize1) windowSize.value = windowSize1
                return IntOffset(0, y)
            }
        }
    }

    val widthPx = windowSize.value.width
    val heightPx = fixedHeightPx ?: (windowSize.value.height - y).coerceAtLeast(0)

    Popup(
        popupPositionProvider = positionProvider,
        properties = PopupProperties(
            focusable = false,
            // With clipping enabled the platform nudges a popup back on-screen; that would drag the
            // below-header scrim up over the header and swallow taps meant for the search field.
            clippingEnabled = false,
        ),
    ) {
        Box(
            Modifier
                .size(
                    width = with(density) { widthPx.toDp() },
                    height = with(density) { heightPx.toDp() },
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { onDismiss() }
        )
    }
}
