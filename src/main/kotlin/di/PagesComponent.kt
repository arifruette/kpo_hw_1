package di

import dagger.Component
import presentation.pages.AddAnimalPage
import presentation.pages.MainPage

@Component(
    modules = [PagesModule::class],
    dependencies = [VetClinicComponent::class, ZooComponent::class]
)
interface PagesComponent {
    val mainPage: MainPage
    val addAnimalPage: AddAnimalPage
}