package di

import dagger.Module
import dagger.Provides
import domain.VetClinic
import presentation.Zoo
import presentation.pages.AddAnimalPage
import presentation.pages.MainPage

@Module
object PagesModule {

    @Provides
    fun provideMainPage() = MainPage()

    @Provides
    fun provideAddAnimalPage(
        vetClinic: VetClinic,
        zoo: Zoo
    ) = AddAnimalPage(vetClinic, zoo)
}