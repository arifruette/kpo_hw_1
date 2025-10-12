package di.console

import dagger.Subcomponent
import domain.contract.ConsoleAgent

@Subcomponent
interface ConsoleAgentComponent {

    val consoleAgent: ConsoleAgent

    @Subcomponent.Factory
    interface Factory {
        fun create(): ConsoleAgentComponent
    }
}