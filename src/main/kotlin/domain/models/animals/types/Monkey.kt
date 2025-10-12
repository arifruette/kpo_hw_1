package domain.models.animals.types

import domain.models.animals.Herbivore

data class Monkey(
    override val kindness: Int,
    override val health: Int,
    override val food: Int,
    override val name: String,
    override val type: AnimalType = AnimalType.MONKEY
) : Herbivore()