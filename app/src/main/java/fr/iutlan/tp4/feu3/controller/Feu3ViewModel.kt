package fr.iutlan.tp4.feu3.controller

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fr.iutlan.tp4.feu3.state.Feu3State

class Feu3ViewModel : ViewModel() {

    // Singleton containing the state, observable but private
    private val _state = mutableStateOf(Feu3State())

    // Getter to access this state outside this class, but private setter
    var state
        get() = _state.value // _state.value = instance of Feu3State
        private set(newState) {
            _state.value = newState // replace the state with the new one
        }

    init {
        reset()
    }

    // Methods to modify the data
    fun reset() {
        state = Feu3State()
    }

    fun suivant() {
        state = when {
            state.rouge -> Feu3State(rouge = false, orange = false, vert = true)
            state.vert -> Feu3State(rouge = false, orange = true, vert = false)
            else -> Feu3State(rouge = true, orange = false, vert = false)
        }
    }
}