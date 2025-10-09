import di.pages.DaggerPagesComponent
import di.vetclinic.DaggerVetClinicComponent
import di.zoo.DaggerZooComponent
import presentation.pages.AddAnimalPage
import presentation.pages.DefaultPageRenderer
import presentation.pages.Page
import kotlin.reflect.KClass

fun main() {
    val vetClinicComponent = DaggerVetClinicComponent.create()

    val zooComponent = DaggerZooComponent.builder()
        .vetClinicComponent(vetClinicComponent)
        .build()

    val pagesComponent = DaggerPagesComponent.builder()
        .vetClinicComponent(vetClinicComponent)
        .zooComponent(zooComponent)
        .build()

    val screens: MutableMap<KClass<out Page>, Page> = mutableMapOf()
    val mainPage = pagesComponent.mainPage
    screens[mainPage::class] = mainPage
    screens[AddAnimalPage::class] = pagesComponent.addAnimalPage

    val defaultPageRenderer = DefaultPageRenderer(screens = screens, startPage = mainPage)
    defaultPageRenderer.startRendering()
}