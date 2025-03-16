package fr.iutlan.tp4.carrefour.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iutlan.tp4.carrefour.controller.CarrefourViewModel
import fr.iutlan.tp4.carrefour.state.CarrefourState

import fr.iutlan.tp4.feu3.state.Feu3State

@Composable
fun MainActivityCarrefourView(viewModel: CarrefourViewModel = viewModel()) {
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CarrefourView(state, modifier = Modifier.padding(16.dp))
        Button(
            onClick = { viewModel.suivant() },
            modifier = Modifier.fillMaxWidth().padding(32.dp)
        ) {
            Text(text = "État suivant")
        }
    }
}

@Composable
fun CarrefourView(state: CarrefourState, modifier: Modifier = Modifier, size: Dp = 180.dp) {
    Box(
        modifier.fillMaxWidth().padding(60.dp)
    ) {
        val mod = Modifier.scale(0.5f).align(Alignment.Center)
        Feu3ViewV3(state = state.feux[0], modifier = mod.offset(y = -size))
        Feu3ViewV3(state = state.feux[1], modifier = mod.offset(x = size))
        Feu3ViewV3(state = state.feux[2], modifier = mod.offset(y = size))
        Feu3ViewV3(state = state.feux[3], modifier = mod.offset(x = -size))
    }
}

@Composable
fun Feu3ViewV3(state: Feu3State, modifier: Modifier) {
    Box(
        modifier = modifier
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
