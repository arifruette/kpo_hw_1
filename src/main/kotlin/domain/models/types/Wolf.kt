package domain.models.types

import domain.InventoryValueHolder.nextInventoryNumber
import domain.models.Predator

data class Wolf(
    override val health: Int,
    override val food: Int,
    override val name: String,
    override val number: Long = nextInventoryNumber,
) : Predator()