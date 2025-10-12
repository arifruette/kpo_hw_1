package di.zoo

import dagger.Subcomponent
import data.zoo.Zoo

@Subcomponent
interface ZooComponent {
    val zoo: Zoo

    @Subcomponent.Factory
    interface Factory {
        fun create(): ZooComponent
    }
}