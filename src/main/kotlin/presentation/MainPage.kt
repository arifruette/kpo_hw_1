package presentation

import javax.inject.Inject

class MainPage @Inject constructor(
    private val zoo: Zoo
) : Page() {
    override fun render() {
        do {
            printChoices()
        } while (handleUserInput())
    }

    private fun printChoices() {
        println(
            listOf(
                "1. Сделать хуйню",
                "2. Выйти"
            ).joinToString(separator = "\n")
        )
    }

    private fun handleUserInput(): Boolean =
        when (readLine()) {
            "1" -> true.also { makeHuina() }
            else -> false
        }

    private fun makeHuina() {
        println("FSDKFJSL")
    }
}