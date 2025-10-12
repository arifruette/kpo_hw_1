package di.vetclinic

import dagger.Subcomponent
import domain.contract.VetClinic

@Subcomponent
interface VetClinicComponent {
    val clinic: VetClinic

    @Subcomponent.Factory
    interface Factory {
        fun create(): VetClinicComponent
    }
}