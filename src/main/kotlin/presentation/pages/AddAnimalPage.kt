package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.models.animals.types.AnimalType
import domain.models.animals.types.Monkey
import domain.models.animals.types.Rabbit
import domain.models.animals.types.Tiger
import domain.models.animals.types.Wolf
import presentation.Page
import javax.inject.Inject

/**
 * Страница добавления животного в зоопарк
 */
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
        consoleAgent.showInfo(AnimalType.typesList)
        val enteredType = consoleAgent.promptInteger(ANIMAL_TYPE)
        val animalType = AnimalType.entries.firstOrNull {
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

    private fun createAnimal(animalType: AnimalType, name: String, health: Int, food: Int) = when (animalType) {
        AnimalType.MONKEY -> {
            val kindness = consoleAgent.promptInteger(KINDNESS_LEVEL)
            Monkey(
                kindness = kindness,
                health = health,
                food = food,
                name = name
            )
        }

        AnimalType.RABBIT -> {
            val kindness = consoleAgent.promptInteger(KINDNESS_LEVEL)
            Rabbit(
                kindness = kindness,
                health = health,
                food = food,
                name = name
            )
        }

        AnimalType.TIGER -> {
            Tiger(
                health = health,
                food = food,
                name = name
            )
        }

        AnimalType.WOLF -> {
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