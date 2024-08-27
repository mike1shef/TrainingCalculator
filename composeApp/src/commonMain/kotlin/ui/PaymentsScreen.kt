package ui

import MainViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel

class PaymentsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<MainViewModel>()
        viewModel.calcTrainingsToPay()
        val valueToPay by viewModel.valueToPay.collectAsState()

        Scaffold(
            topBar = { CustomTopAppBar(navigator, "Payments") }
        ) { paddingValues ->
            Column (
                verticalArrangement = Arrangement.spacedBy(-8.dp)
            ) {
                PayForUnpaidPayments(valueToPay)
                HistoryElement(true)
            }
        }
    }


    @Composable
    fun PayForUnpaidPayments(amountToPay: Double) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 16.dp),

            ) {
            Text(
                text = "Your balance",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "$$amountToPay",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 64.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.onBackground
            )
            Row {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 4.dp, vertical = 8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonColors(
                        containerColor = Color.Green.copy(0.9f),
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = Color.Green.copy(0.5f),
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Pay for all trainings",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Black.copy(1f),
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                    )
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 4.dp, vertical = 8.dp),
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(2.dp, Color.Green.copy(0.5f))
                ) {
                    Text(
                        text = "Pay for selected",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

    }


    @Composable
    fun HistoryElement(isVisible : Boolean){
        if (!isVisible) return
        Card (
            modifier = Modifier
                .fillMaxSize(),
            colors = CardColors(
                containerColor = Color.Black.copy(1f),
                contentColor = MaterialTheme.colorScheme.onTertiary,
                disabledContentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.onBackground),
            shape = MaterialTheme.shapes.medium
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopStart,
                ) {
                    Text(
                        text = "Payments history",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onPrimary

                    )
                    TextButton(
                        onClick = {},
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = "View all",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 4.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
            PaymentHistory()
            //ScheduleElement()
        }
    }

    @Composable
    fun ScheduleElement(){
        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.onBackground),
            shape = MaterialTheme.shapes.medium
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart,
            ) {
                Text(
                    text = "Schedule",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.onPrimary

                )
                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "View all",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }

    @Composable
    fun PaymentHistory(){
        LazyColumn (
            modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 16.dp),
        ) {
            item { PaymentHistoryElement() }
            item { PaymentHistoryElement() }
        }

    }

    @Composable
    fun PaymentHistoryElement(){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row (modifier = Modifier.fillMaxWidth()) {
                Column (
                    modifier = Modifier.weight(0.5f)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .alignByBaseline()
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                        text = "Payment"
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                        text = "18.11.2024")
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                    text = "$ 30.0")

            }

        }
    }
}