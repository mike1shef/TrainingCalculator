import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class Payments : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {CustomTopAppBar(navigator, "Payments")}
        ) {paddingValues ->
            Box { Text("Payments") }
        }
    }
}

data class Payment(
    val id : Double,
    var amount : Double,
    val date : Long
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar (navigator: Navigator, title : String){
    TopAppBar(
        title = { Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        navigationIcon = {
            IconButton(onClick = { navigator.pop() } ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Arrow Icon"
                )
            }
        },
        contentColor = Color.Black,
        backgroundColor = Color.Transparent
    )
}

