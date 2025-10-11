package presentation.pages

abstract class Page {

    val ANSI_RED: String = "\u001B[31m"

    abstract fun render()

    abstract fun handleUserInput(): HandleResult

    fun showError(message: String) {
        println(ANSI_RED + message)
    }

    fun showInfo(message: String) {
        println(message)
    }

    fun prompt(message: String, defaultValue: String = ""): String {
        println(message)
        return readLine() ?: defaultValue
    }

    fun promptInteger(message: String, defaultValue: Int = 0): Int {
        println(message)
        return readLine()?.toIntOrNull() ?: defaultValue
    }

    fun waitButtonPress() {
        println("Нажмите Enter чтобы продолжить")
        readLine()
    }
}