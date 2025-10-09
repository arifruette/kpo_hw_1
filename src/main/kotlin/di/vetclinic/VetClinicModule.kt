package di.vetclinic

import dagger.Module
import dagger.Provides
import domain.contract.VetClinic
import presentation.VetClinicImpl

@Module
object VetClinicModule {

    @Provides
    fun provideVetClinic(): VetClinic = VetClinicImpl()
}