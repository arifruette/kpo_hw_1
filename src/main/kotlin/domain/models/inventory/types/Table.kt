package domain.models.inventory.types

import domain.models.inventory.Thing

data class Table(override val type: ThingType = ThingType.TABLE) : Thing()