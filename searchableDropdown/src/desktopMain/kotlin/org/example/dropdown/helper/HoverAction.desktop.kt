package org.example.dropdown.helper

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
actual fun Modifier.hoverEffect(onHover: (Boolean) -> Unit): Modifier =
    this.onPointerEvent(PointerEventType.Companion.Enter) { onHover(true) }
        .onPointerEvent(PointerEventType.Companion.Exit) { onHover(false) }

