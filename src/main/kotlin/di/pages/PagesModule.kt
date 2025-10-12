package di.pages

import dagger.Module
import dagger.Provides
import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.contract.ReportBuilder
import presentation.pages.AddAnimalPage
import presentation.pages.AddInventoryPage
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
        consoleAgent: ConsoleAgent,
        reportBuilder: ReportBuilder
    ) = KindAnimalsInfoPage(zoo, consoleAgent, reportBuilder)

    @Provides
    @Singleton
    fun provideAddInventoryPage(
        zoo: Zoo,
        consoleAgent: ConsoleAgent,
    ) = AddInventoryPage(zoo, consoleAgent)
}