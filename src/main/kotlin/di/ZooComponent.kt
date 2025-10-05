package di

import dagger.Component
import presentation.Zoo

@Component(modules = [ZooModule::class], dependencies = [VetClinicComponent::class])
interface ZooComponent {
    val zoo: Zoo
}