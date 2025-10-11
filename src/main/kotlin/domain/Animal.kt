package domain

import domain.contract.Alive
import domain.contract.Inventory

abstract class Animal(): Alive, Inventory {
    abstract val health: Int
    abstract val name: String
}