package domain.models.inventory

import domain.InventoryValueHolder
import domain.contract.Inventory

abstract class Thing: Inventory {
    override val number: Long = InventoryValueHolder.nextInventoryNumber
}