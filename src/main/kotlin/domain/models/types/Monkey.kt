package domain.models.types

import domain.InventoryValueHolder.nextInventoryNumber
import domain.models.Herbivore

data class Monkey(
    override val kindness: Int,
    override val health: Int,
    override val food: Int,
    override val name: String,
    override val number: Int = nextInventoryNumber
) : Herbivore()