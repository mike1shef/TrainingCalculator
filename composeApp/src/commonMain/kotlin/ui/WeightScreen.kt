package ui

import MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import database.model.WeightForUI
import org.koin.compose.viewmodel.koinViewModel

class WeightScreen : Screen {
    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow

        Scaffold (
            topBar = { CustomTopAppBar(navigator = navigator, title = "Weight details") },
            modifier = Modifier.fillMaxSize()
        ) { paddingvalues ->
            Column (
                modifier = Modifier
                    .padding(paddingvalues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                WeightHistory()
            }
        }
    }


    @Composable
    fun WeightHistory() {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.onBackground
            ),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopStart,
                ) {
                    Text(
                        text = "History",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.background

                    )
                }
            }
                History()
        }
    }

    @Composable
    fun History() {
        val viewModel = koinViewModel<MainViewModel>()
        val weights = viewModel.weightDifference(viewModel.weights.collectAsState().value)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.onBackground
            ),
            shape = MaterialTheme.shapes.small,

            ){

            LazyColumn(
                modifier = Modifier.padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(-10.dp)
            ) {
                items(weights) { weight ->
                    WeightHistoryElement(weight)
                }
            }

        }
    }

    @Composable
    fun WeightHistoryElement(weightForUI: WeightForUI) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(0.33f)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .alignByBaseline()
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    text = weightForUI.date,
                    color = MaterialTheme.colorScheme.primary.copy(0.5f)
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(0.33f),
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                text = "${weightForUI.weight} kg",
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(0.2f),
                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                text = weightForUI.difference,
                color =
                when (weightForUI.difference.first()) {
                    '+' -> Color.Red.copy(0.4f)
                    '-' -> Color.Green.copy(1f)
                    else -> Color.Black.copy(0.5f)
                }
            )
        }
    }

}
