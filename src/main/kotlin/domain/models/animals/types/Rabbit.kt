package domain.models.animals.types

import domain.models.animals.Herbivore

data class Rabbit(
    override val kindness: Int,
    override val health: Int,
    override val food: Int,
    override val name: String
) : Herbivore()