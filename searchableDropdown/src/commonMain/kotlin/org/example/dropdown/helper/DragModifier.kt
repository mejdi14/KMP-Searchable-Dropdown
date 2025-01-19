package org.example.dropdown.helper

/*
@Composable
fun DragModifier(): Modifier  =
    Modifier.graphicsLayer {
    if (isDragging) {
        translationY = dragOffset.y
        scaleX = 1.05f
        scaleY = 1.05f
    }
}
.pointerInput(Unit) {
    detectDragGesturesAfterLongPress(
        onDragStart = { offset ->
            draggedItem = item
            draggedItemIndex = index
        },
        onDrag = { change, offset ->
            change.consume()
            dragOffset = Offset(0f, dragOffset.y + offset.y)

            val itemHeight = 76f
            val dragAmount = dragOffset.y
            val targetIndex =
                (draggedItemIndex!! + (dragAmount / itemHeight).toInt())
                    .coerceIn(0, filteredItems.size - 1)

            if (targetIndex != draggedItemIndex) {
                val newList = currentList.toMutableList()
                val fromIndex = draggedItemIndex!!
                val toIndex = targetIndex

                newList[fromIndex] = newList[toIndex].also {
                    newList[toIndex] = newList[fromIndex]
                }

                currentList = newList
                draggedItemIndex = targetIndex
                dragOffset = Offset.Zero
            }
        },
        onDragEnd = {
            scope.launch {
            }

            draggedItem = null
            draggedItemIndex = null
            dragOffset = Offset.Zero
        },
        onDragCancel = {
            draggedItem = null
            draggedItemIndex = null
            dragOffset = Offset.Zero
            currentList = filteredItems
        }
    )
}*/

private fun calculateTargetIndex(
    offsetY: Float,
    currentIndex: Int,
    listSize: Int
): Int {
    val itemHeight = 80f
    val targetPosition = currentIndex + (offsetY / itemHeight).toInt()
    return targetPosition.coerceIn(0, listSize - 1)
}