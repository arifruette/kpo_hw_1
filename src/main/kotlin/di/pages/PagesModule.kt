package di.pages

import dagger.Module
import dagger.Provides
import domain.Zoo
import presentation.AddAnimalPage
import presentation.FoodInfoPage
import presentation.KindAnimalsPage
import presentation.MainPage
import javax.inject.Singleton

@Module
object PagesModule {

    @Provides
    @Singleton
    fun provideMainPage() = MainPage()

    @Provides
    @Singleton
    fun provideAddAnimalPage(
        zoo: Zoo
    ) = AddAnimalPage(zoo)

    @Provides
    @Singleton
    fun provideFoodInfoPage(zoo: Zoo) = FoodInfoPage(zoo)

    @Provides
    @Singleton
    fun provideKindAnimalsPage(zoo: Zoo) = KindAnimalsPage(zoo)
}