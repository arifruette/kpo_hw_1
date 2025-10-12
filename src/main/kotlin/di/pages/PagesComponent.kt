package di.pages

import dagger.Subcomponent
import presentation.pages.AddAnimalPage
import presentation.pages.FoodInfoPage
import presentation.pages.KindAnimalsInfoPage
import presentation.pages.MainPage

@Subcomponent
interface PagesComponent {
    val mainPage: MainPage
    val addAnimalPage: AddAnimalPage
    val foodInfoPage: FoodInfoPage
    val kindAnimalsInfoPage: KindAnimalsInfoPage

    @Subcomponent.Factory
    interface Factory {
        fun create(): PagesComponent
    }
}