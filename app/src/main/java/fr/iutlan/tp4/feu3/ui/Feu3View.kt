package fr.iutlan.tp4.feu3.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iutlan.tp4.feu3.controller.Feu3ViewModel
import fr.iutlan.tp4.feu3.state.Feu3State

@Composable
fun MainActivityFeu3View(viewModel: Feu3ViewModel = viewModel()) {
    val state = viewModel.state
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Feu3ViewV1(state, modifier = Modifier.padding(16.dp))
        Button(
            onClick = { viewModel.suivant() },
            modifier = Modifier.fillMaxWidth().padding(32.dp)
        ) {
            Text(text = "État suivant")
        }
    }
}

@Composable
fun Feu3ViewV1(state: Feu3State, modifier: Modifier = Modifier) {
    Text(
        text = "Feu ${state.nomCouleur}",
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}