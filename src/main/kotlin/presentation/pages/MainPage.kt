package presentation.pages

import javax.inject.Inject

class MainPage @Inject constructor() : Page() {

    override fun render() {
        println(
            listOf(
                "1. Добавить новое животное",
                "2. Получить информацию по количеству еды",
                "3. Получить информацию о добрых животных (контактный зоопарк)",
                "4. Ревизия (вывод всего с инвентарными номерами)",
                "5. Выйти",
            ).joinToString(separator = "\n")
        )
    }

    override fun handleUserInput(): HandleResult =
        when (readLine()) {
            "1" -> HandleResult.Push(AddAnimalPage::class)
            else -> HandleResult.Finish
        }

}