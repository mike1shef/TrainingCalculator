package ui

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

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