package di.pages

import dagger.Module
import dagger.Provides
import presentation.Zoo
import presentation.pages.AddAnimalPage
import presentation.pages.MainPage

@Module
object PagesModule {

    @Provides
    fun provideMainPage() = MainPage()

    @Provides
    fun provideAddAnimalPage(
        zoo: Zoo
    ) = AddAnimalPage(zoo)
}