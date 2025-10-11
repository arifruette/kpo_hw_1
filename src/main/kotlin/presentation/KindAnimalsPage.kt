package presentation

import domain.Zoo
import domain.models.Herbivore
import javax.inject.Inject

class KindAnimalsPage @Inject constructor(
    private val zoo: Zoo
) : Page() {
    override fun render() {
        showInfo("Животные которые могут быть отправлены в зоопарк:")
        var curIndex = 1
        val kindAnimalsInfo = buildString {
            zoo.animals.filterIsInstance<Herbivore>().forEach {
                if (it.kindness > 5) {
                    appendLine("${curIndex++}. ${it.name} с номером ${it.number}")
                }
            }
        }
        val resultOutput = kindAnimalsInfo.ifEmpty { "Таких животных не найдено :(" }
        showInfo(resultOutput)
    }
}