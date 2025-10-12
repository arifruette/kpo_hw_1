package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.contract.ReportBuilder
import presentation.Page
import javax.inject.Inject

class RevisionPage @Inject constructor(
    override val consoleAgent: ConsoleAgent,
    private val reportBuilder: ReportBuilder,
    private val zoo: Zoo
) : Page() {
    override fun render() {
        consoleAgent.showInfo(INVENT_REPORT)
        if (zoo.animals.isEmpty() && zoo.things.isEmpty()) {
            consoleAgent.showError(NO_THINGS)
            return
        }
        var curCounter = 1
        zoo.animals.forEach { animal ->
            reportBuilder.appendLine("${curCounter++}. Животное ${animal.name}(${animal.type.animalName}) с номером ${animal.number}")
        }
        zoo.things.forEach { thing ->
            reportBuilder.appendLine("${curCounter++}. Предмет ${thing.type.thingName} с номером ${thing.number}")
        }
        reportBuilder.appendLine("Всего животных: ${zoo.animals.size}")
        reportBuilder.appendLine("Всего вещей: ${zoo.things.size}")
        consoleAgent.showInfo(
            reportBuilder.build()
        )
    }

    companion object {
        const val INVENT_REPORT = "Отчет о вещах и животных на учете: "
        const val NO_THINGS = "На учете ничего нет :("
    }
}