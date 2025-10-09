package di.vetclinic

import dagger.Component
import domain.contract.VetClinic

@Component(modules = [VetClinicModule::class])
interface VetClinicComponent {
    val clinic: VetClinic
}