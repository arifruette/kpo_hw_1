package di.pages

import dagger.Module
import dagger.Provides
import domain.Zoo
import domain.contract.ConsoleAgent
import presentation.pages.AddAnimalPage
import presentation.pages.FoodInfoPage
import presentation.pages.KindAnimalsInfoPage
import presentation.pages.MainPage
import javax.inject.Singleton

@Module
object PagesModule {

    @Provides
    @Singleton
    fun provideMainPage(
        consoleAgent: ConsoleAgent,
    ) = MainPage(consoleAgent)

    @Provides
    @Singleton
    fun provideAddAnimalPage(
        zoo: Zoo,
        consoleAgent: ConsoleAgent,
    ) = AddAnimalPage(zoo, consoleAgent)

    @Provides
    @Singleton
    fun provideFoodInfoPage(
        zoo: Zoo,
        consoleAgent: ConsoleAgent
    ) = FoodInfoPage(zoo, consoleAgent)

    @Provides
    @Singleton
    fun provideKindAnimalsPage(
        zoo: Zoo,
        consoleAgent: ConsoleAgent
    ) = KindAnimalsInfoPage(zoo, consoleAgent)
}