package UI

import MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
            topBar = {CustomTopAppBar(navigator, "Payments")}
        ) {paddingValues ->
            Box { Text(
                text = "$valueToPay") }
        }
    }
}