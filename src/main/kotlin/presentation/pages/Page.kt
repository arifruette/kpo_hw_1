package presentation.pages

abstract class Page {

    abstract fun render()

    abstract fun handleUserInput(): HandleResult

}