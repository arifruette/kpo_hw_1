package di.console

import dagger.Binds
import dagger.Module
import domain.DefaultConsoleAgent
import domain.contract.ConsoleAgent
import javax.inject.Singleton

@Module
interface ConsoleAgentModule {

    @Binds
    @Singleton
    fun bindConsoleAgent(defaultConsoleAgent: DefaultConsoleAgent): ConsoleAgent
}