package fr.iutlan.tp4.carrefour.state

import fr.iutlan.tp4.feu3.state.Feu3State
import fr.iutlan.tp4.feu3.state.FeuCouleur

@Suppress("ArrayInDataClass")
data class CarrefourState(
    val courant: Int = 0, // Index of the current active traffic light
    val feux: Array<Feu3State> = arrayOf(
        Feu3State(rouge = true, orange = false, vert = false), // Initial state: all lights are red
        Feu3State(rouge = true, orange = false, vert = false),
        Feu3State(rouge = true, orange = false, vert = false),
        Feu3State(rouge = true, orange = false, vert = false)
    )
) {
    /**
     * Returns the current traffic light state.
     * @return The Feu3State of the current traffic light.
     */
    val feuCourant get() = feux[courant]

    /**
     * Returns a new CarrefourState with the color of the current traffic light updated.
     * @param couleur The new color for the current traffic light.
     * @return A new CarrefourState with the updated color.
     */
    fun copyChangeCouleurCourant(couleur: FeuCouleur): CarrefourState {
        val newFeux = feux.copyOf()
        newFeux[courant] = newFeux[courant].copyChangeCouleur(couleur.toString())
        return this.copy(feux = newFeux)
    }

    /**
     * Returns a new CarrefourState with the current traffic light index updated.
     * @param num The new index for the current traffic light.
     * @return A new CarrefourState with the updated index.
     */
    fun copyChangeCourant(num: Int): CarrefourState {
        return this.copy(courant = num % feux.size)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CarrefourState

        if (courant != other.courant) return false
        if (!feux.contentEquals(other.feux)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = courant
        result = 31 * result + feux.contentHashCode()
        return result
    }
}