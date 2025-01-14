package org.example.project.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.expand_less
import org.example.dropdown.ui.SearchArea
import org.jetbrains.compose.resources.painterResource
import kotlin.reflect.KProperty1


@Composable
fun <T : Any> SearchableDropdown(
    items: List<T>,
    searchProperties: List<KProperty1<T, *>>,
    dismissOnClickOutside: Boolean = true,
    itemContent: @Composable (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateDpAsState(targetValue = if (expanded) 180.dp else 0.dp)
    val separationSpace = 20

    // Reference to measure the position of the button
    val buttonRef = remember { mutableStateOf<LayoutCoordinates?>(null) }

    Box(
        Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                buttonRef.value = coordinates
            }
            .clickable {
                if (dismissOnClickOutside && !expanded)
                    expanded = !expanded
            }
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = "Select your skill",
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(vertical = 16.dp)
        )

        Box(modifier = Modifier.align(alignment = Alignment.CenterEnd)) {
            AnimatedIcon(rotationAngle, expanded)
        }
    }

    if (expanded) {
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(
                x = 0,
                y = (buttonRef.value?.positionInRoot()?.y?.toInt() ?: 0) +
                        (buttonRef.value?.size?.height ?: 0) + separationSpace
            ),
            onDismissRequest = {
                if (dismissOnClickOutside)
                    expanded = false
            },
            properties = PopupProperties(focusable = true)
        ) {
            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(500)) + expandVertically(
                        animationSpec = tween(1000),
                        expandFrom = Alignment.Top
                    ) togetherWith fadeOut(animationSpec = tween(300))
                }
            ) { isExpanded ->
                if (isExpanded) {
                    var searchQuery = remember { mutableStateOf("") }
                    Column(
                        Modifier
                            .width(with(LocalDensity.current) {
                                buttonRef.value?.size?.width?.toDp() ?: 300.dp
                            })
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .animateContentSize()
                    ) {
                        SearchArea(searchQuery)
                        LazyColumn {
                            val filteredItems = if (searchQuery.value.isEmpty()) {
                                items
                            } else {
                                items.filter { item ->
                                    searchProperties.any { prop ->
                                        try {
                                            val value = prop.get(item)
                                            value?.toString()
                                                ?.contains(
                                                    searchQuery.value,
                                                    ignoreCase = true
                                                ) == true
                                        } catch (e: Exception) {
                                            false
                                        }
                                    }
                                }
                            }
                            items(filteredItems) { item ->
                                itemContent(item)
                            }
                        }
                    }

                }
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun AnimatedIcon(rotationAngle: Dp, expanded: Boolean) {
    val scale = remember { Animatable(1f) }
    val alpha = remember { Animatable(1f) }
    val isFirstComposition = remember { mutableStateOf(true) }

    LaunchedEffect(expanded) {
        if (isFirstComposition.value) {
            isFirstComposition.value = false
        } else {
            if (expanded) {
                scale.animateTo(0.1f, animationSpec = TweenSpec(durationMillis = 500))
                scale.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
            } else {
                scale.animateTo(0.1f, animationSpec = TweenSpec(durationMillis = 500))
                scale.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
            }
        }
    }
    Image(
        painter = painterResource(Res.drawable.expand_less),
        contentDescription = "Toggle Dropdown",
        modifier = Modifier
            .scale(scale.value)
            .alpha(scale.value)
            .rotate(rotationAngle.value)
    )
}

