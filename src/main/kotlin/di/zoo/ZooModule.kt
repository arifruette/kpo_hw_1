package di.zoo

import dagger.Module
import dagger.Provides
import domain.contract.VetClinic
import presentation.Zoo

@Module
object ZooModule {

    @Provides
    fun provideZoo(vetClinic: VetClinic): Zoo {
        return Zoo(vetClinic)
    }
}