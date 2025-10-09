package presentation.pages

import domain.VetClinic
import presentation.Zoo
import javax.inject.Inject

class AddAnimalPage @Inject constructor(
    private val vetClinic: VetClinic,
    private val zoo: Zoo
): Page() {

    override fun render() {
        print("Введите имя животного: ")
        val name = readLine()
        print("Введите состояние здоровья: ")
        val health = readLine()?.toIntOrNull() ?: 0
        val validationResult = vetClinic.checkAnimalHealth(healthLevel = health)

    }

    override fun handleUserInput(): HandleResult {
        TODO("Not yet implemented")
    }
}