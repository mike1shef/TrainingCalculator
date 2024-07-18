package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            ScreenList()
        }
    }
}

@Composable
fun ScreenList(){
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            WeightElement()
        }
        item {
            SkMuscleElement()
        }
        item {
            BodyFatElement()
        }
        item {
            BmiElement()
        }
        item {
            BmrElement()
        }
    }
}

@Composable
fun WeightElement(){
    OutlinedCard(
        enabled = true,
        onClick = { /*TODO*/ },
        modifier = Modifier.size(120.dp, 256.dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 16.dp),

            ) {
            Text(
                text = "Weight",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.TopStart),
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "50",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 80.sp,
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun SkMuscleElement(){
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(120.dp, 120.dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 16.dp),

            ){
            Text(
                text = "Skeletal muscle",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.TopStart),
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "24.4",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "kg",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }
}

@Composable
fun BodyFatElement(){
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(120.dp, 120.dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),

            ){
            Text(
                text = "Body fat",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.TopStart),
                color = MaterialTheme.colorScheme.secondary
            )

            Row(
                modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "11",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }
}

@Composable
fun BmiElement(){
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(120.dp, 120.dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),

            ) {
            Text(
                text = "Body Mass Index",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.TopStart),
                color = MaterialTheme.colorScheme.secondary
            )

            Row(
                modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "18.5",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "km/m",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }
}

@Composable
fun BmrElement() {
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(120.dp, 120.dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),

            ) {
            Text(
                text = "Basal Metabolic Rate",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.TopStart),
                color = MaterialTheme.colorScheme.secondary
            )

            Row(
                modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "1334",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "kcal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }
}
