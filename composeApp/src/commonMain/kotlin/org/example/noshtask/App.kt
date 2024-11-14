package org.example.noshtask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import noshtask.composeapp.generated.resources.Res
import noshtask.composeapp.generated.resources.compose_multiplatform
import org.example.noshtask.di.appModule
import org.koin.compose.KoinApplication
import presentation.screens.CookScreen

@Composable
@Preview
fun App() {
    KoinApplication(application = { modules(appModule()) }) {
        MaterialTheme {
            var showContent by remember { mutableStateOf(false) }

            CookScreen()

            /* Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                 Button(onClick = { showContent = !showContent }) {
                     Text("Click me!")
                 }
                 AnimatedVisibility(showContent) {
                     val greeting = remember { Greeting().greet() }
                     Column(
                         Modifier.fillMaxWidth(),
                         horizontalAlignment = Alignment.CenterHorizontally
                     ) {
                         Image(painterResource(Res.drawable.compose_multiplatform), null)
                         Text("Compose: $greeting")
                     }
                 }
            }*/
        }
    }
}