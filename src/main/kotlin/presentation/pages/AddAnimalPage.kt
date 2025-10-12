package presentation.pages

import domain.Zoo
import domain.contract.ConsoleAgent
import domain.models.types.*
import presentation.Page
import javax.inject.Inject

class AddAnimalPage @Inject constructor(
    private val zoo: Zoo,
    override val consoleAgent: ConsoleAgent
) : Page() {

    override fun render() {
        val name = consoleAgent.prompt(ENTER_NAME, defaultValue = "Пасхалко")
        val health = consoleAgent.promptInteger(HEALTH_LEVEL)
        if (health !in 0..10) {
            consoleAgent.showError(INCORRECT_DATA)
            return
        }
        consoleAgent.showInfo(AllAnimalTypes.typesList)
        val enteredType = consoleAgent.promptInteger(ANIMAL_TYPE)
        val animalType = AllAnimalTypes.entries.firstOrNull {
            it.ordinal + 1 == enteredType
        }
        if (animalType == null) {
            consoleAgent.showError(NO_SUCH_TYPE)
            return
        }
        val food = consoleAgent.promptInteger(FOOD_LEVEL)
        val newAnimal = createAnimal(
            animalType = animalType,
            name = name,
            health = health,
            food = food
        )
        if (zoo.addAnimal(newAnimal)) {
            consoleAgent.showInfo("Животное $name с номером ${newAnimal.number} успешно добавлено")
        } else {
            consoleAgent.showError(NOT_PASSED)
        }
    }

    private fun createAnimal(animalType: AllAnimalTypes, name: String, health: Int, food: Int) = when (animalType) {
        AllAnimalTypes.MONKEY -> {
            val kindness = consoleAgent.promptInteger(KINDNESS_LEVEL)
            Monkey(
                kindness = kindness,
                health = health,
                food = food,
                name = name
            )
        }

        AllAnimalTypes.RABBIT -> {
            val kindness = consoleAgent.promptInteger(KINDNESS_LEVEL)
            Rabbit(
                kindness = kindness,
                health = health,
                food = food,
                name = name
            )
        }

        AllAnimalTypes.TIGER -> {
            Tiger(
                health = health,
                food = food,
                name = name
            )
        }

        AllAnimalTypes.WOLF -> {
            Wolf(
                health = health,
                food = food,
                name = name
            )
        }
    }

    companion object {
        const val ENTER_NAME = "Введите имя животного: "
        const val INCORRECT_DATA = "Введены некорректные данные"
        const val HEALTH_LEVEL = "Введите состояние здоровья (число от 1 до 10): "
        const val ANIMAL_TYPE = "Введите тип животного: "
        const val NO_SUCH_TYPE = "Такого типа не существует"
        const val FOOD_LEVEL = "Сколько еды потребляет ваше животное: (целое число)"
        const val KINDNESS_LEVEL = "Введите уровень доброты: "
        const val NOT_PASSED = "Животное не прошло проверку клиники :("
    }
}