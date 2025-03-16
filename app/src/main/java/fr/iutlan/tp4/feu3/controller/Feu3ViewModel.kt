package fr.iutlan.tp4.feu3.controller


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fr.iutlan.tp4.feu3.state.Feu3State


class Feu3ViewModel : ViewModel() {
// singleton contenant l


    private val _state = mutableStateOf(Feu3State())
// getter pour consulter cet état à l



    var state
        get() = _state.value // _state.value = instance de Feu3State
        private set(newstate) {
            _state.value = newstate // remplace l



        }
    init {
        reset()
    }
    /// méthodes pour modifier les données
    fun reset() {
        state = Feu3State()
    }
    fun suivant() {
        state = with (state) {
            if (rouge) {
                Feu3State(false, false, true)
            } else if (vert) {
                Feu3State(false, true, false)
            } else {
                Feu3State(true, false, false)
            }
        }
    }
}