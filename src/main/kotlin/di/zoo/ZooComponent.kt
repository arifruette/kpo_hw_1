package di.zoo

import dagger.Subcomponent
import domain.Zoo

@Subcomponent
interface ZooComponent {
    val zoo: Zoo

    @Subcomponent.Factory
    interface Factory {
        fun create(): ZooComponent
    }
}