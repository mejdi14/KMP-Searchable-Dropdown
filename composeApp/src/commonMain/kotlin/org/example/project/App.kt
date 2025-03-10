package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.demo.CountryDemo
import org.example.project.demo.PeopleDemo
import org.example.project.demo.SettingsDemo
import org.example.project.demo.StudentDemo
import org.example.project.multipleDemo.MultipleAgentDemo
import org.example.project.multipleDemo.MultipleCountryDemo
import org.example.project.multipleDemo.MultiplePeopleDemo
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFfafafa)
        ) {

            MobileDemo()
        }
    }
}

@Composable
private fun MobileDemo() {
    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        Spacer(modifier = Modifier.height(50.dp))
        MultiplePeopleDemo()
        Spacer(modifier = Modifier.height(50.dp))
        MultipleCountryDemo()
        Spacer(modifier = Modifier.height(50.dp))
        //SettingsDemo()
        MultipleAgentDemo()
    }
}





