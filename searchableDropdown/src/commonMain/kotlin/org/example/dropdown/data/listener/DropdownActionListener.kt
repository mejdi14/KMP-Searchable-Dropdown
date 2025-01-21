package org.example.dropdown.data.listener


interface DropdownActionListener {
    // Default empty implementations
    fun onExpandListener(): Boolean {
        return false
    }

    fun onDoubleClick() {}
    fun onLongPress() {}
    fun onDragStart() {}

    companion object {
        fun create(
            singleClick: (() -> Unit)? = null,
            doubleClick: (() -> Unit)? = null,
            longPress: (() -> Unit)? = null,
            dragStart: (() -> Unit)? = null
        ): DropdownActionListener {
            return object : DropdownActionListener {
                override fun onExpandListener(): Boolean {
                    singleClick?.invoke()
                    return false
                }

                override fun onDoubleClick() {
                    doubleClick?.invoke()
                }

                override fun onLongPress() {
                    longPress?.invoke()
                }

                override fun onDragStart() {
                    dragStart?.invoke()
                }
            }
        }
    }
}