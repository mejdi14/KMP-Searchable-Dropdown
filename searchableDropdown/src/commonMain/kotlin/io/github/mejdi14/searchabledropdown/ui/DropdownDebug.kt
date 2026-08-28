package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.runtime.mutableStateOf

/**
 * TEMPORARY diagnostics sink. Remove once the header-search clear/close and above-gap issues are
 * resolved. The demo renders [events] and [position] in an on-screen banner.
 */
object DropdownDebug {
    val events = mutableStateOf("(no events yet)")
    val position = mutableStateOf("")

    private val recent = ArrayDeque<String>()

    /** Records a discrete event (a tap / dismiss path). Keeps the last few, newest last. */
    fun emit(message: String) {
        recent.addLast(message)
        while (recent.size > 5) recent.removeFirst()
        events.value = recent.joinToString("  |  ")
        println("DROPDOWN_DEBUG: $message")
    }

    /** Records the popup's current placement (updated on every layout; de-duplicated). */
    fun setPosition(message: String) {
        if (position.value != message) {
            position.value = message
            println("DROPDOWN_DEBUG_POS: $message")
        }
    }
}
