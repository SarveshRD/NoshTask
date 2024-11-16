package presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleCookingTimeDialog(
    onDelete: () -> Unit,
    onReschedule: () -> Unit,
    onCookNow: () -> Unit,
    onDismiss: () -> Unit,
) {
    var selectedHour by remember { mutableStateOf(6) }
    var selectedMinute by remember { mutableStateOf(30) }
    var isAm by remember { mutableStateOf(true) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        containerColor = Color.White,
        modifier = Modifier,
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.padding(start = 18.dp),
                text = "Schedule cooking time",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .background(Color(0xFFF5F5F5), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Gray
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePickerComponent(
                modifier = Modifier.fillMaxWidth()
            ) { hour, minute, amPm ->
                selectedHour = hour
                selectedMinute = minute
                isAm = amPm == "AM"
                println("Selected time: $hour:$minute $amPm")
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Delete Button
                TextButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .background(Color.Transparent)
                ) {
                    Text(
                        text = "Delete",
                        color = Color.Red,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        ),
                        fontSize = 16.sp,
                    )
                }

                // Outline Button
                OutlinedButton(
                    shape = RoundedCornerShape(12.dp),
                    onClick = onReschedule,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    border = BorderStroke(0.8.dp, Color(0xFFFF5722))
                ) {
                    Text(
                        text = "Re-Schedule",
                        color = Color(0xFFFF5722),
                        fontSize = 14.sp
                    )
                }

                // Cook Now Button
                Button(
                    onClick = onCookNow,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF5722))
                ) {
                    Text(
                        text = "Cook Now",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
        Spacer(Modifier.padding(16.dp))
    }
}

@Composable
fun TimePickerComponent(
    modifier: Modifier = Modifier,
    initialHour: Int = 10,
    initialMinute: Int = 30,
    initialAMPM: String = "AM",
    onTimeSelected: (Int, Int,String) -> Unit = { _, _ ,_-> }
) {
    var selectedHour by remember { mutableStateOf(initialHour) }
    var selectedMinute by remember { mutableStateOf(initialMinute) }
    var amPm by remember { mutableStateOf(initialAMPM) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8F3FD)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(20.dp)
            ) {
                // Hour picker
                Picker(
                    range = 1..12,
                    selectedValue = selectedHour,
                    visibleItems = 3,
                    onValueChange = {
                        selectedHour = it
                        onTimeSelected(selectedHour, selectedMinute, amPm)
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Minute picker
                Picker(
                    range = 0..59,
                    selectedValue = selectedMinute,
                    visibleItems = 3,
                    onValueChange = {
                        selectedMinute = it
                        onTimeSelected(selectedHour, selectedMinute, amPm)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(modifier = Modifier.width(8.dp))

        // AM/PM Switch
        AMPMSelector(
            selectedAMPM = amPm,
            onAMPMSelected = { newAMPM ->
                amPm = newAMPM
                onTimeSelected(selectedHour, selectedMinute, amPm)
            }
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun AMPMSelector(
    selectedAMPM: String,
    onAMPMSelected: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .width(60.dp)
    ) {
        // AM button
        Text(
            text = "AM",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable {
                    onAMPMSelected("AM")
                }
                .background(if (selectedAMPM == "AM") Color(0xFF001F54) else Color(0xFFE0E0F8))
                .padding(vertical = 4.dp, horizontal = 8.dp),
            color = if (selectedAMPM == "AM") Color.White else Color(0xFF001F54),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        // PM button
        Text(
            text = "PM",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable {
                    onAMPMSelected("PM")
                }
                .background(if (selectedAMPM == "PM") Color(0xFF001F54) else Color(0xFFE0E0F8))
                .padding(vertical = 4.dp, horizontal = 8.dp),
            color = if (selectedAMPM == "PM") Color.White else Color(0xFF001F54),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun Picker(
    range: IntRange,
    selectedValue: Int,
    visibleItems: Int = 3,
    onValueChange: (Int) -> Unit
) {
    val itemHeight = 40.dp
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = range.indexOf(selectedValue))
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(lazyListState.firstVisibleItemIndex) {
        val centerIndex = lazyListState.firstVisibleItemIndex + visibleItems / 2
        val selectedItem = range.elementAt(centerIndex % range.count())
        onValueChange(selectedItem)
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .height(itemHeight * visibleItems)
            .width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = itemHeight)
    ) {
        itemsIndexed(range.toList()) { index, item ->
            val isSelected = item == selectedValue
            val color = if (isSelected) Color(0xFF001F54) else Color.Gray
            val fontSize = if (isSelected) 34.sp else 30.sp

            Box(
                modifier = Modifier
                    .height(itemHeight)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .clickable {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(index)
                        }
                        onValueChange(item)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.toString().padStart(2, '0'),
                    fontSize = fontSize,
                    color = color,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}