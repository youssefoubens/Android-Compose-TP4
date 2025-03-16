package fr.iutlan.tp4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import fr.iutlan.tp4.feu3.ui.MainActivityCarrefourView
import fr.iutlan.tp4.feu3.ui.MainActivityFeu3View
// Ou si tu veux lancer Carrefour directement
// import fr.iutlan.tp4.carrefour.ui.MainActivityCarrefourView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityFeu3View()
            // Ou si tu veux lancer Carrefour
             MainActivityCarrefourView()
        }
    }
}
