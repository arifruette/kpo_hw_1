package presentation.pages

import domain.Zoo
import domain.contract.ConsoleAgent
import presentation.Page
import javax.inject.Inject

/**
 * Страница вывода отчета о потребляемой еде
 */
class FoodInfoPage @Inject constructor(
    private val zoo: Zoo,
    override val consoleAgent: ConsoleAgent
) : Page() {
    override fun render() {
        if (zoo.animals.isEmpty()) {
            consoleAgent.showError(NO_ANIMALS)
            return
        }
        consoleAgent.showInfo(REPORT_FOOD)
        var totalFood = 0
        consoleAgent.showInfo(buildString {
            zoo.animals.forEach { animal ->
                totalFood += animal.food
                appendLine("${animal.name} с номером ${animal.number} потребляет ${animal.food}кг еды")
            }
        })
        consoleAgent.showInfo("Всего: ${totalFood}кг")
    }

    companion object {
        const val NO_ANIMALS = "Животных пока нет :("
        const val REPORT_FOOD = "Отчет о потребляемой еде: "
    }
}