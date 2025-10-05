package di

import dagger.Binds
import dagger.Module
import domain.VetClinic
import presentation.VetClinicImpl
import javax.inject.Singleton

@Module
interface VetClinicModule {

    @Binds
    fun bindVetClinic(vetClinicImpl: VetClinicImpl): VetClinic
}