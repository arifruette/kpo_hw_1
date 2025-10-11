import di.DaggerAppComponent
import presentation.AddAnimalPage
import presentation.DefaultPageRenderer
import presentation.FoodInfoPage
import presentation.KindAnimalsPage
import presentation.Page
import kotlin.reflect.KClass

fun main() {
    val appComponent = DaggerAppComponent.create()

    val pagesComponent = appComponent.pagesComponent().create()

    val screens: MutableMap<KClass<out Page>, Page> = mutableMapOf()
    val mainPage = pagesComponent.mainPage

    screens[mainPage::class] = mainPage
    screens[AddAnimalPage::class] = pagesComponent.addAnimalPage
    screens[FoodInfoPage::class] = pagesComponent.foodInfoPage
    screens[KindAnimalsPage::class] = pagesComponent.kindAnimalsPage

    val defaultPageRenderer = DefaultPageRenderer(screens = screens, startPage = mainPage)
    defaultPageRenderer.startRendering()
}