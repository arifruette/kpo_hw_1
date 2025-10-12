package presentation.pages

import domain.contract.ConsoleAgent
import presentation.Page
import presentation.contract.FinishRenderResult
import javax.inject.Inject

/**
 * Главная страница с менюшкой
 */
class MainPage @Inject constructor(override val consoleAgent: ConsoleAgent) : Page() {

    override fun render() {
        consoleAgent.showInfo(
            listOf(
                "1. Добавить новое животное",
                "2. Получить информацию по количеству еды",
                "3. Получить информацию о добрых животных (контактный зоопарк)",
                "4. Добавить инвентарный предмет",
                "5. Ревизия (вывод всего с инвентарными номерами)",
                "6. Выйти"
            ).joinToString(separator = "\n")
        )
    }

    override fun finishRendering(): FinishRenderResult =
        when (consoleAgent.prompt("Введите действие: ")) {
            "1" -> FinishRenderResult.Push(AddAnimalPage::class)
            "2" -> FinishRenderResult.Push(FoodInfoPage::class)
            "3" -> FinishRenderResult.Push(KindAnimalsInfoPage::class)
            else -> FinishRenderResult.Finish
        }

}