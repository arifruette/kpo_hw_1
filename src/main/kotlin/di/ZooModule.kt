package di

import dagger.Module
import dagger.Provides
import domain.VetClinic
import presentation.Zoo
import javax.inject.Singleton

@Module
object ZooModule {

    @Singleton
    @Provides
    fun provideZoo(vetClinic: VetClinic): Zoo {
        return Zoo(vetClinic)
    }
}