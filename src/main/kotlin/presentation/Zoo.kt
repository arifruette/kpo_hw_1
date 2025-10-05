package presentation

import domain.Animal
import domain.VetClinic
import javax.inject.Inject

class Zoo @Inject constructor(
    private val vetClinic: VetClinic
) {
    @Inject lateinit var animal: Animal
}