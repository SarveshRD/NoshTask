package org.example.noshtask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import navigation.root.RootComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import noshtask.composeapp.generated.resources.Res
import noshtask.composeapp.generated.resources.compose_multiplatform
import noshtask.composeapp.generated.resources.cook_image
import noshtask.composeapp.generated.resources.devices
import noshtask.composeapp.generated.resources.favorite
import noshtask.composeapp.generated.resources.img
import noshtask.composeapp.generated.resources.pref
import noshtask.composeapp.generated.resources.setting
import org.example.noshtask.di.appModule
import org.koin.compose.KoinApplication
import presentation.screens.CookScreen
import presentation.screens.DeviceScreen
import presentation.screens.FavoriteScreen
import presentation.screens.ManualScreen
import presentation.screens.PreferenceScreen
import presentation.screens.SettingScreen

@Composable
fun App(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {
    val routerState by rootComponent.routerState.subscribeAsState()


    KoinApplication(application = { modules(appModule()) }) {
        MaterialTheme {
            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        //containerColor = Color.White,
                        modifier = Modifier,
                        contentColor = Color.White,
                        backgroundColor = Color.White,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(
                                modifier= Modifier.clickable { rootComponent.navigateToCook() },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.cook_image),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Cook",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )
                            }
                            Column(
                                modifier= Modifier.clickable { rootComponent.navigateToFavorite() },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.favorite),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Favourites",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )
                            }
                            Column(
                                modifier= Modifier.clickable { rootComponent.navigateToManual() },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.img),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Manual",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )
                            }
                            Column(
                                modifier= Modifier.clickable { rootComponent.navigateToDevice() },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.devices),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Device",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )
                            }
                            Column(
                                modifier= Modifier.clickable { rootComponent.navigateToPreferences() },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.pref),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Preferences",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )
                            }
                            Column(
                                modifier= Modifier.clickable { rootComponent.navigateToSettings() },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.setting),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Settings",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )
                            }

                        }
                    }
                }
            ) {
                Surface(
                    modifier = modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)
                ) {
                    when (val child = routerState.active.instance) {
                        is RootComponent.RootChild.Cook -> {
                            CookScreen(
                                child.component
                            )
                        }
                        is RootComponent.RootChild.Device -> {
                            DeviceScreen(
                                child.component
                            )
                        }
                        is RootComponent.RootChild.Favorite -> {
                            FavoriteScreen(
                                child.component
                            )
                        }
                        is RootComponent.RootChild.Manual -> {
                            ManualScreen(
                                child.component
                            )
                        }
                        is RootComponent.RootChild.Preferences -> {
                            PreferenceScreen(
                                child.component
                            )
                        }
                        is RootComponent.RootChild.Settings -> {
                            SettingScreen(
                                child.component
                            )
                        }
                    }
                }
            }
        }
    }
}