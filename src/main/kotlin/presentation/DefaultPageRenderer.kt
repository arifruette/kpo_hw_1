package presentation

import presentation.contract.FinishRenderResult
import presentation.pages.MainPage
import java.util.*
import kotlin.reflect.KClass

class DefaultPageRenderer(
    private val screens: Map<KClass<out Page>, Page>,
    private val startPage: Page
) {

    private val _pageStack = Stack<Page>().also { it.push(startPage) }

    fun startRendering() {
        do {
            val curPage = _pageStack.peek()
            curPage.render()
            val handleResult = curPage.finishRendering()
            when (handleResult) {
                is FinishRenderResult.Push -> {
                    val newScreen = screens[handleResult.page]
                    checkNotNull(newScreen) {
                        "Экран ${handleResult.page.simpleName} не был найден"
                    }
                    push(newScreen)
                }

                is FinishRenderResult.Pop -> pop()
                is FinishRenderResult.PopToMain -> popToMain()
                is FinishRenderResult.Finish -> break
            }
            clearConsole()
        } while (true)
    }

    /**
     * Метод для удаления экрана из стека
     * @throws IllegalStateException если стек пустой
     */
    fun pop() {
        if (_pageStack.empty()) {
            throw IllegalStateException("page stack is empty")
        }
        _pageStack.pop()
    }

    fun push(page: Page) {
        _pageStack.push(page)
    }

    fun popToMain() {
        while (!_pageStack.empty() && _pageStack.peek() !is MainPage) {
            _pageStack.pop()
        }
        if (_pageStack.empty()) {
            throw IllegalStateException("page stack is empty")
        }
    }

    private fun clearConsole() = println("\u001B[0m")
}