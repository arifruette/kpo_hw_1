package domain.animals

import domain.Predator

class Wolf(
    override val health: Int,
    override val food: Int,
    override val number: Int
) : Predator()