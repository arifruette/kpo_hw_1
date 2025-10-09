package di

import dagger.Module
import dagger.Provides
import domain.VetClinic
import presentation.VetClinicImpl

@Module
object VetClinicModule {

    @Provides
    fun provideVetClinic(): VetClinic = VetClinicImpl()
}