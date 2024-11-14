package presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import noshtask.composeapp.generated.resources.Res
import noshtask.composeapp.generated.resources.compose_multiplatform
import noshtask.composeapp.generated.resources.cook_image
import noshtask.composeapp.generated.resources.devices
import noshtask.composeapp.generated.resources.favorite
import noshtask.composeapp.generated.resources.img
import noshtask.composeapp.generated.resources.logout
import noshtask.composeapp.generated.resources.non_veg
import noshtask.composeapp.generated.resources.notification
import noshtask.composeapp.generated.resources.pasta
import noshtask.composeapp.generated.resources.pref
import noshtask.composeapp.generated.resources.prepared_cook
import noshtask.composeapp.generated.resources.setting
import org.example.noshtask.data.model.response.JsonResponse
import org.example.noshtask.data.model.response.JsonResponseItem
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import presentation.viewModel.CookViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CookScreen() {


    var search by remember { mutableStateOf("") }
    var itemList: List<JsonResponseItem>? = null

    var response: JsonResponse? = null

    val cookViewModel: CookViewModel = koinInject()
    val cookScreenState by cookViewModel.cookViewState.collectAsState()

    LaunchedEffect(Unit) {
        cookViewModel.getData()
    }

    when (cookScreenState) {
        is CookViewModel.CookScreenState.Loading -> {
        }

        is CookViewModel.CookScreenState.Success -> {
            response =
                (cookScreenState as CookViewModel.CookScreenState.Success).responseData
            println("*************Hello $response")
            itemList = response
        }

        is CookViewModel.CookScreenState.Error -> {
            val error =
                (cookScreenState as CookViewModel.CookScreenState.Error).errorMessage
            println("responseError${error}")
        }

        else -> {}
    }

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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            stickyHeader {
                // search & filter
                Row(
                    modifier = Modifier.padding(top = 18.dp, start = 4.dp, end = 18.dp)
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //search
                    SearchComponent(
                        modifier = Modifier
                            .weight(1f),
                        label = "Search for dish",
                        value = search,
                        onValueChange = { search = it },
                        fieldColor = Color.White,
                        imeAction = ImeAction.Search
                    )

                    Image(
                        painter = painterResource(Res.drawable.notification),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                            .clip(CircleShape)
                    )

                    Image(
                        painter = painterResource(Res.drawable.logout),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                            .clip(CircleShape)
                    )


                }
            }

            //card
            item {
                Card(
                    modifier = Modifier,
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = Color.DarkGray,
                    elevation = 8.dp,
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.padding(3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFAC94F4))
                                .clip(CircleShape)
                            ,
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.pasta),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.size(50.dp)
                                .clip(CircleShape)
                                .padding(8.dp)
                            )
                        }
                        Spacer(Modifier.padding(2.dp))
                        Column {
                            Text(
                                modifier = Modifier.padding(end = 8.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                text = "Italian Spaghetti...",
                                fontSize = 16.sp,
                                color = Color.White,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Text(
                                modifier = Modifier.padding(end = 8.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                text = "Scheduled 6:30 AM",
                                fontSize = 14.sp,
                                color = Color.White,
                                style = TextStyle(
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }
                }
            }


            //what on your mind
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = "What's on your mind?",
                    fontSize = 18.sp,
                    color = Color.Blue,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.padding(10.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        items = itemList.orEmpty()
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = RoundedCornerShape(20.dp),
                            backgroundColor = Color.White,
                            elevation = 8.dp,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.padding(3.dp)
                            ) {
                                AsyncImage(
                                    model = it.imageUrl,
                                    modifier = Modifier.size(45.dp).clip(CircleShape),
                                    placeholder = painterResource(Res.drawable.pasta),
                                    contentScale = ContentScale.FillBounds,
                                    contentDescription = "",
                                )
                                Spacer(Modifier.padding(2.dp))
                                Text(
                                    modifier = Modifier.padding(end = 8.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    text = it.dishName,
                                    fontSize = 16.sp,
                                    color = Color.Blue,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            }
                        }
                    }

                }
            }

            //recommendation
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier,
                        //.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = "Recommendations",
                        fontSize = 20.sp,
                        color = Color.Blue,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        modifier = Modifier,
                        // .fillMaxWidth(),
                        textAlign = TextAlign.End,
                        text = "Show all",
                        fontSize = 16.sp,
                        color = Color.Blue,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    // verticalAlignment = Alignment.,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = itemList.orEmpty()
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            backgroundColor = Color.White,//Color(0xF8855C),
                            elevation = 4.dp,
                        ) {

                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    contentAlignment = Alignment.BottomCenter
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .size(180.dp)
                                            .padding(10.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color.LightGray)
                                    ) {
                                        Image(
                                            painter = painterResource(Res.drawable.pasta),
                                            contentDescription = null,
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier.size(180.dp)
                                            //.clip(CircleShape)
                                            // .padding(12.dp)
                                        )
                                        Image(
                                            painter = painterResource(Res.drawable.non_veg),
                                            contentDescription = null,
                                            modifier = Modifier.size(30.dp)
                                                .align(Alignment.TopEnd)
                                                .padding(3.dp)
                                                .zIndex(1f)
                                        )
                                    }

                                    Card(
                                        modifier = Modifier,
                                        /*.width(69.dp)
                                        .height(36.dp)*/
                                        elevation = 0.dp,
                                        shape = RoundedCornerShape(16.dp),
                                        backgroundColor = Color(0xFFFC9706),
                                    ) {
                                        Row() {
                                            // Icon(Icons.Default.Star, contentDescription = "Star")
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(6.dp)
                                                    .background(Color(0xFFA500)),
                                                textAlign = TextAlign.Start,
                                                text = "★ 4.5",
                                                fontSize = 12.sp,
                                                style = TextStyle(
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                    }
                                }

                                Spacer(Modifier.padding(4.dp))
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    text = "Italian Spaghetti Pasta",
                                    fontSize = 18.sp,
                                    style = TextStyle(
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        // modifier = Modifier.fillMaxWidth(),

                                    ) {
                                        Image(
                                            painter = painterResource(Res.drawable.prepared_cook),
                                            contentDescription = null,
                                            modifier = Modifier.size(20.dp)
                                            //.clip(CircleShape)
                                            //.padding(12.dp)
                                        )
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            //   textAlign = TextAlign.Start,
                                            text = "30 min",
                                            fontSize = 11.sp
                                        )
                                    }

                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        //  textAlign = TextAlign.Start,
                                        text = " ● Medium prep",
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }

                }
            }
            //explore
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Card(
                        modifier = Modifier,
                        /*.width(69.dp)
                                .height(36.dp)*/
                        elevation = 0.dp,
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = Color(0xFFFC9706),
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            //  .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Explore all dishes",
                            fontSize = 18.sp,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Card(
                        modifier = Modifier,
                        /*.width(69.dp)
                                .height(36.dp)*/
                        elevation = 0.dp,
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = Color(0xFFFC9706),
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            //  .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Confused what to cook?",
                            fontSize = 18.sp,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

            }

        }
    }

}