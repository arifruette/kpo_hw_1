package presentation

abstract class Page {

    abstract fun render()

    fun clearPage() {
        println("\\033[2J")
    }
}