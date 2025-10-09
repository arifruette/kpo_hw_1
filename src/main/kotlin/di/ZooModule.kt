package di

import dagger.Module
import dagger.Provides
import domain.VetClinic
import presentation.Zoo

@Module
object ZooModule {

    @Provides
    fun provideZoo(vetClinic: VetClinic): Zoo {
        return Zoo(vetClinic)
    }
}