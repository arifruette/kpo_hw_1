package domain.models.inventory.types

enum class ThingType(val thingName: String) {
    TABLE("Стол"),
    COMPUTER("Компьютер");

    companion object {
        val typesList by lazy {
            buildString {
                ThingType.entries.forEachIndexed { index, entry ->
                    appendLine("${index + 1}. ${entry.thingName}")
                }
            }
        }
    }
}