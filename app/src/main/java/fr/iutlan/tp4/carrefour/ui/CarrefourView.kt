package fr.iutlan.tp4.carrefour.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iutlan.tp4.carrefour.controller.CarrefourViewModel
import fr.iutlan.tp4.carrefour.state.CarrefourState
import fr.iutlan.tp4.feu3.state.Feu3State

@Composable
fun MainActivityCarrefourView(viewModel: CarrefourViewModel = viewModel()) {
    val state = viewModel.state

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Traffic Intersection",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )

            // Traffic lights grid
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CarrefourView(state)
            }

            // Next state button
            Button(
                onClick = { viewModel.suivant() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Next State",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Next"
                    )
                }
            }
        }
    }
}

@Composable
fun CarrefourView(state: CarrefourState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Draw the road layout
        RoadLayout()

        // Traffic lights
        val size = 120.dp
        val lightSpacing = size / 2

        // Top traffic light
        TrafficLight(
            feuState = state.feux[0],
            modifier = Modifier.offset(y = -lightSpacing)
        )

        // Right traffic light
        TrafficLight(
            feuState = state.feux[1],
            modifier = Modifier.offset(x = lightSpacing)
        )

        // Bottom traffic light
        TrafficLight(
            feuState = state.feux[2],
            modifier = Modifier.offset(y = lightSpacing)
        )

        // Left traffic light
        TrafficLight(
            feuState = state.feux[3],
            modifier = Modifier.offset(x = -lightSpacing)
        )
    }
}

@Composable
fun RoadLayout() {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val roadWidth = canvasWidth * 0.3f

        // Draw roads (dark gray)
        drawRect(
            color = Color(0xFF444444),
            size = size.copy(width = roadWidth),
            topLeft = Offset(canvasWidth / 2 - roadWidth / 2, 0f)
        )

        drawRect(
            color = Color(0xFF444444),
            size = size.copy(height = roadWidth),
            topLeft = Offset(0f, canvasHeight / 2 - roadWidth / 2)
        )

        // Road markings - Center lines (solid)
        val lineColor = Color.White
        val lineWidth = 2f

        // Vertical center line
        drawLine(
            color = lineColor,
            start = Offset(canvasWidth / 2, 0f),
            end = Offset(canvasWidth / 2, canvasHeight),
            strokeWidth = lineWidth
        )

        // Horizontal center line
        drawLine(
            color = lineColor,
            start = Offset(0f, canvasHeight / 2),
            end = Offset(canvasWidth, canvasHeight / 2),
            strokeWidth = lineWidth
        )
    }
}

@Composable
fun TrafficLight(
    feuState: Feu3State,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF222222))
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Red light
            TrafficLightBulb(
                isOn = feuState.rouge,
                activeColor = Color(0xFFFF0000),
                inactiveColor = Color(0xFF550000)
            )

            // Yellow/Orange light
            TrafficLightBulb(
                isOn = feuState.orange,
                activeColor = Color(0xFFFFAA00),
                inactiveColor = Color(0xFF553300)
            )

            // Green light
            TrafficLightBulb(
                isOn = feuState.vert,
                activeColor = Color(0xFF00CC00),
                inactiveColor = Color(0xFF005500)
            )
        }
    }
}

@Composable
fun TrafficLightBulb(
    isOn: Boolean,
    activeColor: Color,
    inactiveColor: Color,
    modifier: Modifier = Modifier
) {
    val color = if (isOn) activeColor else inactiveColor

    Box(
        modifier = modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Preview(showBackground = true)
@Composable
fun CarrefourPreview() {
    MaterialTheme {
        MainActivityCarrefourView(
            viewModel = object : CarrefourViewModel() {
                override var state = CarrefourState(
                    courant = 1,
                    feux = arrayOf(
                        Feu3State(rouge = true, orange = false, vert = false), // Red
                        Feu3State(rouge = false, orange = false, vert = true), // Green
                        Feu3State(rouge = true, orange = false, vert = false), // Red
                        Feu3State(rouge = false, orange = true, vert = false)  // Orange
                    )
                )
                override fun suivant() {}
            }
        )
    }
}