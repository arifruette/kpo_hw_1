package domain.animals

import domain.Predator

data class Tiger(
    override val health: Int,
    override val food: Int,
    override val name: String
) : Predator()