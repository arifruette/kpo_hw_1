package di.pages

import dagger.Subcomponent
import presentation.pages.AddAnimalPage
import presentation.pages.AddInventoryPage
import presentation.pages.FoodInfoPage
import presentation.pages.KindAnimalsInfoPage
import presentation.pages.MainPage
import presentation.pages.RevisionPage

@Subcomponent
interface PagesComponent {
    val mainPage: MainPage
    val addAnimalPage: AddAnimalPage
    val foodInfoPage: FoodInfoPage
    val kindAnimalsInfoPage: KindAnimalsInfoPage
    val addInventoryPage: AddInventoryPage
    val revisionPage: RevisionPage

    @Subcomponent.Factory
    interface Factory {
        fun create(): PagesComponent
    }
}