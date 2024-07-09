
import UI.MainScreen
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition

@Composable
fun App() {
    MaterialTheme {
        Navigator(MainScreen()) { navigator: Navigator ->
                SlideTransition(navigator)
        }
    }
}