package presentation.pages

import domain.animals.*
import presentation.Zoo
import javax.inject.Inject

class AddAnimalPage @Inject constructor(
    private val zoo: Zoo
) : Page() {

    override fun render() {
        val name = prompt("Введите имя животного: ", defaultValue = "Arifruette")
        val health = promptInteger("Введите состояние здоровья (число от 1 до 10): ")
        if (health !in 0..10) {
            showError("Введены некорректные данные")
            return
        }
        showInfo(AllAnimalTypes.typesList)
        val enteredType = promptInteger("Введите тип животного: ")
        val animalType =
            AllAnimalTypes.entries.firstOrNull { it.ordinal + 1 == enteredType }
        if (animalType == null) {
            showError("Такого типа не существует")
            return
        }
        val food = promptInteger("Сколько еды потребляет ваше животное: (целое число)")
        val newAnimal = when (animalType) {
            AllAnimalTypes.MONKEY -> {
                val kindness = promptInteger("Введите уровень доброты: ")
                Monkey(
                    kindness = kindness,
                    health = health,
                    food = food,
                    name = name
                )
            }

            AllAnimalTypes.RABBIT -> {
                val kindness = promptInteger("Введите уровень доброты: ")
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
        if (zoo.addAnimal(newAnimal)) {
            showInfo("Животное $name успешно добавлено")
        } else {
            showError("Животное не прошло проверку клиники :(")
        }
    }


    override fun handleUserInput(): HandleResult {
        waitButtonPress()
        return HandleResult.Pop
    }
}