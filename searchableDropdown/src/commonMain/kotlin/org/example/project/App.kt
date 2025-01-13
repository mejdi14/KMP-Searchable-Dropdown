package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.compose_multiplatform
import org.example.project.ui.SelectYourSkill

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Cyan
        ) {
            Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                Spacer(modifier = Modifier.height(200.dp))
                SelectYourSkill()
                Spacer(modifier = Modifier.height(200.dp))
                SelectYourSkill()
            }
        }
    }
}