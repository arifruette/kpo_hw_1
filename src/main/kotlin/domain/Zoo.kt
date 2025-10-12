package domain

import domain.models.Animal
import domain.contract.VetClinic
import javax.inject.Inject

/**
 * Непосредственно зоопарк
 */
class Zoo @Inject constructor(
    private val vetClinic: VetClinic
) {
    private val _animals: MutableList<Animal> = ArrayList()

    val animals: List<Animal> = _animals

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
}