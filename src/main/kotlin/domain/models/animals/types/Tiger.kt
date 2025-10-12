package domain.models.animals.types

import domain.models.animals.Predator

data class Tiger(
    override val health: Int,
    override val food: Int,
    override val name: String,
    override val type: AnimalType = AnimalType.TIGER
) : Predator()