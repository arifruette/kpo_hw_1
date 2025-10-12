package domain

import domain.contract.ConsoleAgent
import javax.inject.Inject

class DefaultConsoleAgent @Inject constructor(): ConsoleAgent {
    override fun showError(message: String) {
        println(ANSI_RED + message)
    }

    override fun showInfo(message: String) {
        println(message)
    }

    override fun prompt(message: String, defaultValue: String): String {
        println(message)
        return readLine() ?: defaultValue
    }

    override fun promptInteger(message: String, defaultValue: Int): Int {
        println(message)
        return readLine()?.toIntOrNull() ?: defaultValue
    }

    override fun waitButtonPress() {
        println(PRESS_ENTER)
        readLine()
    }

    companion object {
        const val ANSI_RED: String = "\u001B[31m"
        const val PRESS_ENTER = "Нажмите Enter чтобы продолжить"
    }
}