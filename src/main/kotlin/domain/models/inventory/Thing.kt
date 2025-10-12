package domain.models.inventory

import domain.InventoryValueHolder
import domain.contract.Inventory
import domain.models.inventory.types.ThingType

/**
 * Класс маркер вещи
 */
abstract class Thing: Inventory {
    abstract val type: ThingType
    override val number: Long = InventoryValueHolder.nextInventoryNumber
}