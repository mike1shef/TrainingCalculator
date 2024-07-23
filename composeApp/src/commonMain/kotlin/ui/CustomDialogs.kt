package ui

import MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import database.model.Event
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import utils.localDateChecker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    openDialog: MutableState<Boolean>,
    selectedDate: MutableState<LocalDate>
) {
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds())
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