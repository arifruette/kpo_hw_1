package di.pages

import dagger.Subcomponent
import presentation.AddAnimalPage
import presentation.FoodInfoPage
import presentation.KindAnimalsPage
import presentation.MainPage

@Subcomponent
interface PagesComponent {
    val mainPage: MainPage
    val addAnimalPage: AddAnimalPage
    val foodInfoPage: FoodInfoPage
    val kindAnimalsPage: KindAnimalsPage

    @Subcomponent.Factory
    interface Factory {
        fun create(): PagesComponent
    }
}