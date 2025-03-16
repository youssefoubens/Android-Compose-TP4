package fr.iutlan.tp4.carrefour.state

import fr.iutlan.tp4.feu3.state.Feu3State
import fr.iutlan.tp4.feu3.state.FeuCouleur

data class CarrefourState(
    val courant: Int = 0,
    val feux: Array<Feu3State> = arrayOf(Feu3State(), Feu3State(), Feu3State(), Feu3State())
) {
    val feuCourant get() = feux[courant]

    fun copyChangeCouleurCourant(couleur: FeuCouleur): CarrefourState {
        val newFeux = feux.copyOf()
        newFeux[courant] = newFeux[courant].copyChangeCouleur(couleur.toString())
        return this.copy(feux = newFeux)
    }

    fun copyChangeCourant(num: Int): CarrefourState {
        return this.copy(courant = num % feux.size)
    }
}