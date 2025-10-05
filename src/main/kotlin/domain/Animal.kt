package domain

abstract class Animal(): Alive, Inventory {
    abstract val healthStatus: HealthStatus
}