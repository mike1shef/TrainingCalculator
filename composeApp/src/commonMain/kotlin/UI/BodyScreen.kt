package UI

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class BodyScreen : Screen {
    @Composable
    override fun Content() {
        val navigator : Navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = { CustomTopAppBar(navigator, "Body") }
        ){
            Column {
                Text("Body")
            }
        }
    }
}