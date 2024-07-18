package ui

import MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import database.model.BodyMeasurements
import database.model.Weight
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import utils.getCurrentDate
import utils.localDateChecker

class AddBodyMeasurementScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold (
            topBar = { CustomTopAppBar(navigator = navigator, title = "Add body measurement") }
        ) {
            AddMeasurement(navigator = navigator)
        }
    }

    @Composable
    fun AddMeasurement(navigator: Navigator){
        val currentDate = getCurrentDate()
        val viewModel = koinViewModel<MainViewModel>()
        var showFullData by remember { mutableStateOf(false) }
        var weight by remember { mutableStateOf("") }

        val selectedDate = remember { mutableStateOf(currentDate) }
        val openDialog = remember { mutableStateOf(false) }

        val listOfMeasurements = remember { mutableStateListOf<String>("", "", "", "") } // Store measurements

        Column(
            Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top
            ) {
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it},
                    label = {Text("weight")},
                    modifier = Modifier.weight(0.5f),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                )

                Column (
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(start = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("See all fields")
                    Switch(
                        checked = showFullData,
                        onCheckedChange = { showFullData = it },
                        modifier = Modifier
                            .size(48.dp)
                            .padding(start = 16.dp),
                        thumbContent = {
                            if (showFullData) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    )
                }
            }
            if (showFullData) {
                ShowFullData(
                    list = allMeasurements, listOfMeasurements,
                    onValueChange = { index, newValue ->
                        listOfMeasurements[index] = newValue
                    }
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
                    modifier = Modifier.weight(0.5f),
                    text = "Date: "
                )
                TextButton(
                    modifier = Modifier
                        .weight(0.5f),
                    onClick = { openDialog.value = true}
                ) {
                    val text = localDateChecker(selectedDate.value)
                    Text(text)
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 8.dp),
                onClick = {
                    val weight = Weight(weight = weight, date = selectedDate.value.toString())

                    if (showFullData){
                        val bodyMeasurement = createBodyMeasurement(
                            additionalMeasurements = listOfMeasurements,
                            date = selectedDate.value.toString()
                        )
                        viewModel.addBodyMeasurement(bodyMeasurement)
                        viewModel.addWeight(weight)
                        navigator.pop()
                    } else {
                        viewModel.addWeight(weight)
                        navigator.pop()
                    }
                },
                enabled = checkAvailability(weight, listOfMeasurements, showFullData)
            ) {
                Text(text = "Add Measurement")
            }
        }
        if (openDialog.value) {
            CustomDatePickerDialog(openDialog, selectedDate)
        }
    }

    @Composable
    fun ShowFullData (
        list: List<String>,
        listToRemember: MutableList<String>,
        onValueChange: (Int, String) -> Unit
    ){
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
                items(list.size) { index ->
                    Measurement(text = list[index],
                        value = listToRemember[index]) {
                        newValue ->
                        onValueChange(index, newValue)
                    }
                }
        }
    }

    private val allMeasurements  = listOf("skMuscle", "bodyFat %", "BMI","BMR")

    @Composable
    fun Measurement (text : String, value : String, onValueChange: (String) -> Unit){
        OutlinedTextField(
            value = value,
            label = { Text(text) },
            onValueChange = onValueChange,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
    }
}

fun createBodyMeasurement( additionalMeasurements : List<String>, date : String) : BodyMeasurements{
    return BodyMeasurements(
        skMuscle = additionalMeasurements[0],
        bodyFat = additionalMeasurements[1],
        bmi = additionalMeasurements[2],
        bmr = additionalMeasurements[3],
        date = date
    )
}

fun checkAvailability (weight : String, bodyMeasurements : List<String>, showData : Boolean): Boolean{
    return if (showData) {
        bodyMeasurements.all { it.isNotBlank() }
    } else {
        weight.isNotBlank()
    }
}