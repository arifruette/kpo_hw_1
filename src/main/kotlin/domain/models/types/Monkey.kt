package domain.models.types

import domain.models.Herbivore

data class Monkey(
    override val kindness: Int,
    override val health: Int,
    override val food: Int,
    override val name: String
) : Herbivore()