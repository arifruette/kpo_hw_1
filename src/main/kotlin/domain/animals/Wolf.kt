package domain.animals

import domain.Predator

data class Wolf(
    override val health: Int,
    override val food: Int,
    override val name: String
) : Predator()