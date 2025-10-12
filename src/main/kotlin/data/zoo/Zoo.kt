package data.zoo

import domain.models.animals.Animal
import domain.contract.VetClinic
import domain.models.inventory.Thing
import javax.inject.Inject

/**
 * Непосредственно зоопарк
 */
class Zoo @Inject constructor(
    private val vetClinic: VetClinic
) {
    private val _animals: MutableList<Animal> = ArrayList()
    private val _things: MutableList<Thing> = ArrayList()

    val animals: List<Animal> = _animals
    val things: List<Thing> = _things

    /**
     * Метод добавления животного
     * @return `true` если удалось добавить, `false`, если не прошел проверку
     */
    fun addAnimal(animal: Animal): Boolean {
        val validationResult = vetClinic.checkAnimalHealth(animal.health)
        if (!validationResult) {
            return false
        }
        _animals.add(animal)
        return true
    }

    /**
     * Метод для добавления вещи на учет
     */
    fun addThing(thing: Thing) {
        _things.add(thing)
    }
}