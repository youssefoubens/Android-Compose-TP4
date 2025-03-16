package fr.iutlan.tp4.feu3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iutlan.tp4.carrefour.controller.CarrefourViewModel
import fr.iutlan.tp4.carrefour.state.CarrefourState

import fr.iutlan.tp4.feu3.state.Feu3State
import fr.iutlan.tp4.feu3.ui.Feu3View

@Composable
fun MainActivityCarrefourView(viewmodel: CarrefourViewModel = viewModel()) {
    val state = viewmodel.state
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CarrefourView(state)
        Button(
            onClick = { viewmodel.suivant() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("État suivant")
        }
    }
}

@Composable
fun CarrefourView(state: CarrefourState) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(40.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        state.feux.forEach { Feu3View(it) }
    }
}

@Composable
fun Feu3View(state: Feu3State) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                when {
                    state.rouge -> Color.Red
                    state.orange -> Color(0xFFFFA500) // Orange
                    state.vert -> Color.Green
                    else -> Color.Gray
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = state.nomCouleur, color = Color.White)
    }
}
