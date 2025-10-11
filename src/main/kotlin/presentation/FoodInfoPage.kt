package presentation

import domain.Zoo
import javax.inject.Inject

class FoodInfoPage @Inject constructor(
    private val zoo: Zoo
) : Page() {
    override fun render() {
        if (zoo.animals.isEmpty()) {
            showInfo("Животных пока нет :(")
            return
        }
        showInfo("Отчет о потребляемой еде: ")
        var totalFood = 0
        showInfo(buildString {
            zoo.animals.forEach { animal ->
                totalFood += animal.food
                appendLine("${animal.name} с номером ${animal.number} потребляет ${animal.food}кг еды")
            }
        })
        showInfo("Всего: ${totalFood}кг")
    }
}