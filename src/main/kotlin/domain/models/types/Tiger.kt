package domain.models.types

import domain.models.Predator

data class Tiger(
    override val health: Int,
    override val food: Int,
    override val name: String
) : Predator()