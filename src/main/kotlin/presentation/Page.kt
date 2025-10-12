package presentation

import domain.contract.ConsoleAgent
import presentation.contract.FinishRenderResult

/**
 * Абстрактный класс для страницы которые можно отрисовывать
 */
abstract class Page {

    abstract val consoleAgent: ConsoleAgent

    abstract fun render()

    open fun finishRendering(): FinishRenderResult {
        consoleAgent.waitButtonPress()
        return FinishRenderResult.Pop
    }
}