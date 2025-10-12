package domain.contract

/**
 * Интерфейс ветеринарной клиники
 * (в будущем могут быть вет клиники у которых разные критерии проверки)
 */
interface VetClinic {
    /**
     * Метод проверки здоровья животного
     * @param healthLevel уровень здоровья животного
     * @return `true` если животное здорово и `false` в ином случае
     */
    fun checkAnimalHealth(healthLevel: Int): Boolean
}