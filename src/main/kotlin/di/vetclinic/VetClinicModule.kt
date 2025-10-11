package di.vetclinic

import dagger.Module
import dagger.Provides
import domain.contract.VetClinic
import domain.VetClinicImpl
import javax.inject.Singleton

@Module
object VetClinicModule {

    @Provides
    @Singleton
    fun provideVetClinic(): VetClinic = VetClinicImpl()
}