package presentation.contract

import presentation.Page
import kotlin.reflect.KClass

sealed interface HandleResult {
    data object Pop : HandleResult
    data object PopToMain : HandleResult
    data object Finish : HandleResult
    data class Push(val page: KClass<out Page>) : HandleResult
}