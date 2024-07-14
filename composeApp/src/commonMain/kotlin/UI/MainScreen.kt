package UI

import MainViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import database.model.Event
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import trainingcalculator.composeapp.generated.resources.Res
import trainingcalculator.composeapp.generated.resources.cardio_load_24px
import trainingcalculator.composeapp.generated.resources.payments_24px
import trainingcalculator.composeapp.generated.resources.straighten_24px

class MainScreen () : Screen {
    private val MAIN_MENU = listOf("Trainings", "Body", "Payments")

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<MainViewModel>()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {topAppBar()}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 12.dp, horizontal = 0.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                MainScreenContent(MAIN_MENU)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    MainScreenButtons(viewModel)
                }
            }
        }
    }

    @Composable
    fun MainScreenContent(menuItems: List<String>, ) {
        Column {
            menuItems.forEach { item ->
                MainScreenElement(text = item)
            }
        }
    }

    @Composable
    fun MainScreenElement(text: String) {
        val navigator = LocalNavigator.currentOrThrow

        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .clickable {
                    when (text) {
                        "Trainings" -> navigator.push(TrainingsScreen())
                        "Body" -> navigator.push(BodyScreen())
                        "Payments" -> navigator.push(PaymentsScreen())
                    }
                }
        ) {
            Text(
                text = text,
                fontSize = 28.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            )
        }
    }


    @Composable
    fun MainScreenButtons(viewModel: MainViewModel) {
        Surface(
            modifier = Modifier.padding(2.dp),
            color = MaterialTheme.colors.primary,
            shape = CircleShape,
            elevation = 12.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(12.dp)
                    .size(width = 180.dp, height = 48.dp)

            ) {
                IconButton(
                    modifier = Modifier
                        .weight(0.33f)
                        .size(36.dp),
                    onClick = {
                        viewModel.addTraining(Event(isPaid = false, date = "11.07.2024"))
                    }
                ) {
                    Icon(
                        painterResource(Res.drawable.cardio_load_24px),
                        contentDescription = "Add training"
                    )
                }
                IconButton(
                    modifier = Modifier
                        .weight(0.33f)
                        .size(36.dp),
                    onClick = {}
                ) {
                    Icon(
                        painterResource(Res.drawable.straighten_24px),
                        contentDescription = "Add measurement"
                    )
                }
                IconButton(
                    modifier = Modifier
                        .weight(0.33f)
                        .size(36.dp),
                    onClick = {}
                ) {
                    Icon(
                        painterResource(Res.drawable.payments_24px),
                        contentDescription = "Add payment"
                    )
                }
            }
        }
    }
}