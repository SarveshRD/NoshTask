package presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import navigation.device.DeviceComponent

@Composable
fun DeviceScreen(component: DeviceComponent) {
    Surface(
        Modifier.fillMaxSize()
    ) {
        Box (
            Modifier,
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Device",
                fontSize = 18.sp,
                color = Color.Blue,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}