package org.example.dropdown.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import org.example.dropdown.data.ToggleIcon
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ToggleIconComposable(
    rotationAngle: Dp,
    expanded: Boolean,
    toggleIcon: ToggleIcon,
    modifier: Modifier
) {
    val scale = remember { Animatable(1f) }
    val alpha = remember { Animatable(1f) }
    val isFirstComposition = remember { mutableStateOf(true) }

    LaunchedEffect(expanded) {
        if (isFirstComposition.value) {
            isFirstComposition.value = false
        } else {
            if (expanded) {
                scale.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 300))
                scale.animateTo(1f, animationSpec = TweenSpec(durationMillis = 300))
                alpha.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 300))
                alpha.animateTo(1f, animationSpec = TweenSpec(durationMillis = 300))
            } else {
                scale.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 300))
                scale.animateTo(1f, animationSpec = TweenSpec(durationMillis = 300))
                alpha.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 300))
                alpha.animateTo(1f, animationSpec = TweenSpec(durationMillis = 300))
            }
        }
    }
    Icon(
        painter = painterResource(toggleIcon.iconDrawable),
        contentDescription = toggleIcon.contentDescription,
        tint = toggleIcon.iconTintColor,
        modifier = modifier
            .size(toggleIcon.iconSize)
            .scale(scale.value)
            .rotate(rotationAngle.value)
    )
}