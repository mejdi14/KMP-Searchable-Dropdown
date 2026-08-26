package io.github.mejdi14.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-Searchable-Dropdown",
    ) {
        App()
    }
}