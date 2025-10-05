package di

import dagger.Component
import domain.VetClinic

@Component(modules = [VetClinicModule::class])
interface VetClinicComponent {
    val clinic: VetClinic
}