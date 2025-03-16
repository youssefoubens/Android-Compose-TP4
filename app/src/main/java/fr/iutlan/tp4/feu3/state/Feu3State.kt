package fr.iutlan.tp4.feu3.state

data class Feu3State(
    val rouge: Boolean = true,
    val orange: Boolean = false,
    val vert: Boolean = false,
) {

    val nomCouleur: String
        get() =
            if (rouge) "rouge" else
                if (orange) "orange" else
                    if (vert) "vert" else "???"

    fun copyChangeCouleur(couleur: String): Feu3State {
        return when (couleur.lowercase()) {
            "rouge" -> this.copy(rouge = true, orange = false, vert = false)
            "orange" -> this.copy(rouge = false, orange = true, vert = false)
            "vert" -> this.copy(rouge = false, orange = false, vert = true)
            else -> this // No change if an invalid color is provided
        }
    }
}
