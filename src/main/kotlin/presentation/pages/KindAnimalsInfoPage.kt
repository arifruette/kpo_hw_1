package presentation.pages

import domain.Zoo
import domain.contract.ConsoleAgent
import domain.models.Herbivore
import presentation.Page
import javax.inject.Inject

class KindAnimalsInfoPage @Inject constructor(
    private val zoo: Zoo,
    override val consoleAgent: ConsoleAgent
) : Page() {
    override fun render() {
        consoleAgent.showInfo("Животные которые могут быть отправлены в зоопарк:")
        var curIndex = 1
        val kindAnimalsInfo = buildString {
            zoo.animals.filterIsInstance<Herbivore>().forEach {
                if (it.kindness > 5) {
                    appendLine("${curIndex++}. ${it.name} с номером ${it.number}")
                }
            }
        }
        val resultOutput = kindAnimalsInfo.ifEmpty { "Таких животных не найдено :(" }
        consoleAgent.showInfo(resultOutput)
    }
}