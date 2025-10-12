package domain.models.animals.types

/**
 * Enumb содержащий все типы животных которые можно создать
 */
enum class AnimalType(val animalName: String) {
    MONKEY(animalName = "Обезьяна"),
    RABBIT(animalName = "Кролик"),
    TIGER(animalName = "Тигр"),
    WOLF(animalName = "Волк");

    companion object {
        val typesList by lazy {
            buildString {
                entries.forEachIndexed { index, entry ->
                    appendLine("${index + 1}. ${entry.animalName}")
                }
            }
        }
    }
}