package domain.contract

/**
 * Интерфейс описывающий правила работы с консолью
 */
interface ConsoleAgent {
    fun showInfo(message: String)

    fun showError(message: String)

    fun prompt(message: String, defaultValue: String = ""): String

    fun promptInteger(message: String, defaultValue: Int = 0): Int

    fun waitButtonPress()

}