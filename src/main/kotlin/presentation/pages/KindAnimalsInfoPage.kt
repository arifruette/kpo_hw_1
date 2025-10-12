package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.contract.ReportBuilder
import domain.models.animals.Herbivore
import presentation.Page
import javax.inject.Inject

/**
 * Страница с информацией о животных которых можно отправить в контактный зоопарк
 */
class KindAnimalsInfoPage @Inject constructor(
    private val zoo: Zoo,
    override val consoleAgent: ConsoleAgent,
    private val reportBuilder: ReportBuilder
) : Page() {
    override fun render() {
        reportBuilder.appendLine(KIND_ANIMALS_TITLE)
        var curIndex = 1
        val herbivores = zoo.animals.filterIsInstance<Herbivore>()
        if (herbivores.isNotEmpty()) {
            herbivores.forEach {
                if (it.kindness > 5) {
                    reportBuilder.appendLine(
                        "${curIndex++}. ${it.name} с номером ${it.number}"
                    )
                }
            }
        } else {
            reportBuilder.appendLine(NO_SUCH_ANIMALS)
        }
        consoleAgent.showInfo(reportBuilder.build())
    }

    companion object {
        const val KIND_ANIMALS_TITLE = "Животные которые могут быть отправлены в зоопарк:"
        const val NO_SUCH_ANIMALS = "Таких животных нет :("
    }
}