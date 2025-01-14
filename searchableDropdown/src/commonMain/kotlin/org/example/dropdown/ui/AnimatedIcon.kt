package org.example.dropdown.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.expand_less
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AnimatedIcon(rotationAngle: Dp, expanded: Boolean) {
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