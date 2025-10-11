package domain.contract

import domain.InventoryValueHolder

interface Inventory {
    val number: Int
        get() = InventoryValueHolder.currentInventoryNumber
}