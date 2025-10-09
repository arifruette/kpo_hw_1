package presentation.pages

abstract class Page {

    abstract fun render()

    abstract fun handleUserInput(): HandleResult

    fun showError(message: String) {
        System.err.println(message)
    }

    fun waitButtonPress() {
        println("Нажмите Enter чтобы продолжить")
        readLine()
    }
}