package fr.iutlan.tp4.carrefour.controller

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fr.iutlan.tp4.carrefour.state.CarrefourState
import fr.iutlan.tp4.feu3.state.FeuCouleur

open class CarrefourViewModel : ViewModel() {
    private val _state = mutableStateOf(CarrefourState())
    open var state
        get() = _state.value
        set(newState) {
            _state.value = newState
        }

    open fun suivant() {
        val currentFeuIndex = state.courant
        val feux = state.feux.toMutableList()

        when {
            // If the current light is red, turn it green
            feux[currentFeuIndex].rouge -> {
                feux[currentFeuIndex] = feux[currentFeuIndex].copyChangeCouleur(FeuCouleur.VERT.toString())
            }
            // If the current light is green, turn it orange
            feux[currentFeuIndex].vert -> {
                feux[currentFeuIndex] = feux[currentFeuIndex].copyChangeCouleur(FeuCouleur.ORANGE.toString())
            }
            // If the current light is orange, turn it red and move to the next light
            feux[currentFeuIndex].orange -> {
                feux[currentFeuIndex] = feux[currentFeuIndex].copyChangeCouleur(FeuCouleur.ROUGE.toString())
                val nextFeuIndex = (currentFeuIndex + 1) % feux.size
                feux[nextFeuIndex] = feux[nextFeuIndex].copyChangeCouleur(FeuCouleur.VERT.toString())
                state = state.copyChangeCourant(nextFeuIndex)
            }
        }

        state = state.copy(feux = feux.toTypedArray())
    }
}