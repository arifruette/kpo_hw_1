package domain.models.types

import domain.InventoryValueHolder.nextInventoryNumber
import domain.models.Herbivore

data class Rabbit(
    override val kindness: Int,
    override val health: Int,
    override val food: Int,
    override val name: String,
    override val number: Long = nextInventoryNumber,
) : Herbivore()