import di.DaggerAppComponent
import presentation.pages.AddAnimalPage
import presentation.DefaultPageRenderer
import presentation.pages.FoodInfoPage
import presentation.pages.KindAnimalsInfoPage
import presentation.Page
import presentation.pages.AddInventoryPage
import kotlin.reflect.KClass

fun main() {
    val appComponent = DaggerAppComponent.create()

    val pagesComponent = appComponent.pagesComponent().create()

    val screens: MutableMap<KClass<out Page>, Page> = mutableMapOf()
    val mainPage = pagesComponent.mainPage

    screens[mainPage::class] = mainPage
    screens[AddAnimalPage::class] = pagesComponent.addAnimalPage
    screens[FoodInfoPage::class] = pagesComponent.foodInfoPage
    screens[KindAnimalsInfoPage::class] = pagesComponent.kindAnimalsInfoPage
    screens[AddInventoryPage::class] = pagesComponent.addInventoryPage

    val defaultPageRenderer = DefaultPageRenderer(screens = screens, startPage = mainPage)
    defaultPageRenderer.startRendering()
}