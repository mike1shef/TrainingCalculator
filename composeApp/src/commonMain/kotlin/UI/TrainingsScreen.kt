package UI

import database.Event
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import database.generateEvents

class TrainingsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator : Navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = { CustomTopAppBar(navigator, "Trainings") }
        ){
            Column {
                Schedule(generateEvents())
            }
        }
    }

    @Composable
    fun Schedule (events: List<Event>) {
        LazyColumn {
            items(events){ item: Event ->
                Event(item)
            }
        }
    }

    @Composable
    fun Event (
        event: Event
    ){
        var isSelected by remember { mutableStateOf(false) }
        var status by remember { mutableStateOf(event.isPaid) }

        Row(
            Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable { isSelected = !isSelected }
                .background(
                    if (isSelected) Color(0xFFE0E0E0)
                    else Color.White
                ),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = Modifier.weight(0.85f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = event.name,
                    fontSize = 16.sp
                )
                Text(
                    text = event.date,
                    fontSize = 14.sp,
                    color = Color.Black.copy(0.5f)
                )
            }

            if (!status) { IconButton(
                onClick = { status = true }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Upcoming visit" )
            }
                } else { IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Done, contentDescription = "Done")
            }
            }
        }
    }
}