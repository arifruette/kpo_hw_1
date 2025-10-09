package domain.animals

import domain.Predator

class Tiger(
    override val health: Int,
    override val food: Int,
    override val number: Int
) : Predator()