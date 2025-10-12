package di

import dagger.Component
import di.console.ConsoleAgentModule
import di.pages.PagesComponent
import di.pages.PagesModule
import di.vetclinic.VetClinicComponent
import di.vetclinic.VetClinicModule
import di.zoo.ZooComponent
import di.zoo.ZooModule
import javax.inject.Singleton

@Singleton
@Component(modules = [VetClinicModule::class, ZooModule::class, PagesModule::class, ConsoleAgentModule::class])
interface AppComponent {
    fun vetClinicComponent(): VetClinicComponent.Factory
    fun pagesComponent(): PagesComponent.Factory
    fun zooComponent(): ZooComponent.Factory
}