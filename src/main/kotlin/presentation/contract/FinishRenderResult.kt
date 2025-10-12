package presentation.contract

import presentation.Page
import kotlin.reflect.KClass

sealed interface FinishRenderResult {
    data object Pop : FinishRenderResult
    data object PopToMain : FinishRenderResult
    data object Finish : FinishRenderResult
    data class Push(val page: KClass<out Page>) : FinishRenderResult
}