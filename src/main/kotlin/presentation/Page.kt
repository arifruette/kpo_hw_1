package presentation

import domain.contract.ConsoleAgent
import presentation.contract.FinishRenderResult

abstract class Page {

    protected abstract val consoleAgent: ConsoleAgent

    abstract fun render()

    open fun finishRendering(): FinishRenderResult {
        consoleAgent.waitButtonPress()
        return FinishRenderResult.Pop
    }
}