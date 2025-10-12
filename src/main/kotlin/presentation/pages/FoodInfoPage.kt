package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.contract.ReportBuilder
import presentation.Page
import javax.inject.Inject

/**
 * Страница вывода отчета о потребляемой еде
 */
class FoodInfoPage @Inject constructor(
    private val zoo: Zoo,
    private val reportBuilder: ReportBuilder,
    override val consoleAgent: ConsoleAgent
) : Page() {
    override fun render() {
        if (zoo.animals.isEmpty()) {
            consoleAgent.showError(NO_ANIMALS)
            return
        }
        consoleAgent.showInfo(REPORT_FOOD)
        var totalFood = 0
        zoo.animals.forEach { animal ->
            totalFood += animal.food
            reportBuilder.appendLine("${animal.name} с номером ${animal.number} потребляет ${animal.food}кг еды")
        }
        consoleAgent.showInfo(reportBuilder.build())
        consoleAgent.showInfo("Всего: ${totalFood}кг")
    }

    companion object {
        const val NO_ANIMALS = "Животных пока нет :("
        const val REPORT_FOOD = "Отчет о потребляемой еде: "
    }
}