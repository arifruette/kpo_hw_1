package domain.models

import domain.contract.Alive
import domain.contract.Inventory

/**
 * Абстрактный класс животного
 */
abstract class Animal(): Alive, Inventory {
    abstract val health: Int
    abstract val name: String
}