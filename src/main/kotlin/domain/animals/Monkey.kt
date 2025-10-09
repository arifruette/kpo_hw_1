package domain.animals

import domain.Herbivore

class Monkey(
    override val kindness: Int,
    override val health: Int,
    override val food: Int,
    override val number: Int
) : Herbivore()