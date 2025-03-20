package fr.iutlan.tp4.feu3.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iutlan.tp4.feu3.controller.Feu3ViewModel
import fr.iutlan.tp4.feu3.state.Feu3State

@Composable
fun MainActivityFeu3View(viewModel: Feu3ViewModel = viewModel()) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Increased padding for better spacing
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Feu3ViewV3(state, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(24.dp)) // Increased spacer height
        Button(
            onClick = { viewModel.suivant() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Standard button height
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "État suivant", style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
fun Feu3ViewV1(state: Feu3State, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Added elevation for depth
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Feu ${state.nomCouleur}",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun Feu3ViewV2(state: Feu3State, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(16.dp) // Add padding for better spacing
    ) {
        // Red Light
        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 8.dp) // Add vertical padding
        ) {
            RadioButton(
                selected = state.rouge,
                onClick = null, // Non-reactive
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.outline
                )
            )
            Text(
                text = "Rouge",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Orange Light
        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 8.dp) // Add vertical padding
        ) {
            RadioButton(
                selected = state.orange,
                onClick = null, // Non-reactive
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.outline
                )
            )
            Text(
                text = "Orange",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Green Light
        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 8.dp) // Add vertical padding
        ) {
            RadioButton(
                selected = state.vert,
                onClick = null, // Non-reactive
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.outline
                )
            )
            Text(
                text = "Vert",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

////////

@Composable
fun Feu3ViewV3(state: Feu3State, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
    ) {
        // Traffic Light Container
        Surface(
            modifier = Modifier
                .size(64.dp, 160.dp) // Adjusted size for better proportions
                .clip(RoundedCornerShape(16.dp)),
            tonalElevation = 8.dp, // Add elevation for depth
            color = MaterialTheme.colorScheme.surfaceVariant // Use Material color scheme
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp), // Add padding for spacing
                verticalArrangement = Arrangement.SpaceEvenly, // Distribute lights evenly
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Red Light
                Feu(
                    color = Color.Red,
                    isOn = state.rouge,
                    modifier = Modifier.size(40.dp)
                )

                // Orange Light
                Feu(
                    color = Color.Orange,
                    isOn = state.orange,
                    modifier = Modifier.size(40.dp)
                )

                // Green Light
                Feu(
                    color = Color.Green,
                    isOn = state.vert,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

/**
 * Draws a colored or gray circle based on the `isOn` state.
 */
@Composable
fun Feu(color: Color, isOn: Boolean, modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .padding(4.dp), // Add padding for spacing
        onDraw = {
            drawCircle(
                color = if (isOn) color else Color.Gray.copy(alpha = 0.5f), // Dim the light when off
                radius = size.minDimension / 2
            )
        }
    )
}

// Define the Color.Orange extension
private val Color.Companion.Orange: Color
    get() = hsv(33.0f, 1.0f, 1.0f)

@Preview(showBackground = true)
@Composable
fun Feu3ViewV3Preview() {
    MaterialTheme {
        Feu3ViewV3(
            state = Feu3State(rouge = false, orange = true, vert = false)
        )
    }
}