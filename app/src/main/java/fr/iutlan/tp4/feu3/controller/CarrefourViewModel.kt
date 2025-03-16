package fr.iutlan.tp4.carrefour.controller

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fr.iutlan.tp4.carrefour.state.CarrefourState

import fr.iutlan.tp4.feu3.state.FeuCouleur

import androidx.compose.runtime.mutableStateOf

import fr.iutlan.tp4.carrefour.state.*

class CarrefourViewModel : ViewModel() {
    private val _state = mutableStateOf(CarrefourState())
    var state
        get() = _state.value
        private set(newState) {
            _state.value = newState
        }

    fun suivant() {
        state = if (state.feuCourant.rouge) {
            state.copyChangeCouleurCourant(FeuCouleur.VERT)
        } else if (state.feuCourant.vert) {
            state.copyChangeCouleurCourant(FeuCouleur.ORANGE)
        } else {
            state.copyChangeCouleurCourant(FeuCouleur.ROUGE).copyChangeCourant(state.courant + 1)
        }
    }
}