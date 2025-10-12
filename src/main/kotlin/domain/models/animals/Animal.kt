package domain.models.animals

import domain.InventoryValueHolder
import domain.contract.Alive
import domain.contract.Inventory

/**
 * Абстрактный класс животного
 */
abstract class Animal(): Alive, Inventory {
    abstract val health: Int
    abstract val name: String
    override val number: Long = InventoryValueHolder.nextInventoryNumber
}