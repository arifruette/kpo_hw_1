package di.zoo

import dagger.Component
import di.vetclinic.VetClinicComponent
import presentation.Zoo

@Component(
    modules = [ZooModule::class],
    dependencies = [VetClinicComponent::class]
)
interface ZooComponent {
    val zoo: Zoo
}