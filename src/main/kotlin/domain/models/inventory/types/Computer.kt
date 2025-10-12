package domain.models.inventory.types

import domain.models.inventory.Thing

data class Computer(override val type: ThingType = ThingType.COMPUTER) : Thing()