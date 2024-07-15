package ui

import MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import kotlinx.datetime.*
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import database.model.Event
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import utils.localDateChecker

class AddTrainingScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<MainViewModel>()
        Scaffold(
            topBar = { CustomTopAppBar(navigator = navigator, title = "Add training") }
        ) {
            val now: Instant = Clock.System.now()
            val nowDate: LocalDate = now.toLocalDateTime(TimeZone.currentSystemDefault()).date
            val selectedDate = remember { mutableStateOf(nowDate) }
            val openDialog = remember { mutableStateOf(false) }
            var isPaid by remember { mutableStateOf(false) }

            Column(
                Modifier.padding(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Event type:"
                    )
                    Text(
                        modifier = Modifier.weight(0.25f),
                        text = "Training"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Date: "
                    )
                    TextButton(
                        modifier = Modifier.weight(0.4f),
                        onClick = { openDialog.value = true }
                    ) {
                        Text(localDateChecker(selectedDate.value))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Cost: "
                    )
                    Text(
                        modifier = Modifier.weight(0.25f),
                        text = "${30.0}"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Paid: "
                    )
                    Switch(
                        checked = isPaid,
                        onCheckedChange = { isPaid = it },
                        modifier = Modifier
                            .size(48.dp)
                            .padding(start = 16.dp)
                            .weight(0.4f),
                        thumbContent = {
                            if (isPaid) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 8.dp),
                    onClick = {
                        viewModel.addTraining(
                            Event(date = selectedDate.value.toString(),isPaid = isPaid))
                        navigator.pop()
                    }
                ) {
                    Text(text = "Add Training")
                }
            }
            if (openDialog.value) {
                val datePickerState =
                    rememberDatePickerState(initialSelectedDateMillis = now.toEpochMilliseconds())
                val confirmEnabled =
                    { derivedStateOf { datePickerState.selectedDateMillis != null } }
                DatePickerDialog(
                    onDismissRequest = { openDialog.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                val instantSelectedDate = Instant.fromEpochMilliseconds(datePickerState.selectedDateMillis!!)
                                    .toLocalDateTime(TimeZone.UTC)
                                    .date
                                selectedDate.value = instantSelectedDate
                            },
                            enabled = confirmEnabled().value
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { openDialog.value = false }
                        ) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}
