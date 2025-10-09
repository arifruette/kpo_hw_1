package presentation.pages

import presentation.Zoo
import javax.inject.Inject

class AddAnimalPage @Inject constructor(
    private val zoo: Zoo
): Page() {

    override fun render() {
        print("Введите имя животного: ")
        val name = readLine()
        print("Введите состояние здоровья (число от 1 до 10): ")
        val health = readLine()?.toIntOrNull() ?: 0
        if (health < 0 || health > 10) {
            showError("Плохо ввел")
        }
    }

    override fun handleUserInput(): HandleResult {
        waitButtonPress()
        return HandleResult.Pop
    }
}