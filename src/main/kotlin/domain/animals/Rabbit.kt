package domain.animals

import domain.Herbivore

data class Rabbit(
    override val kindness: Int,
    override val health: Int,
    override val food: Int,
    override val name: String
) : Herbivore()