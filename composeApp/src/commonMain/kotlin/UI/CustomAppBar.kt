package UI

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.navigator.Navigator

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