package domain.models.types

import domain.InventoryValueHolder.nextInventoryNumber
import domain.models.Predator

data class Tiger(
    override val health: Int,
    override val food: Int,
    override val name: String,
    override val number: Int = nextInventoryNumber,
) : Predator()