package di.zoo

import dagger.Module
import dagger.Provides
import domain.contract.VetClinic
import domain.Zoo
import javax.inject.Singleton

@Module
object ZooModule {

    @Provides
    @Singleton
    fun provideZoo(vetClinic: VetClinic): Zoo {
        return Zoo(vetClinic)
    }
}