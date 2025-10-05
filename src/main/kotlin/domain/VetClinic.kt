package domain

/**
 * Интерфейс ветеринарной клиники
 * (в будущем могут быть вет клиники у которых разные критерии проверки)
 */
interface VetClinic {
    /**
     * Метод проверки здоровья животного
     * @param animal животное
     * @return `true` если животное здорово и `false` в ином случае
     */
    fun checkAnimalHealth(animal: Animal): Boolean
}