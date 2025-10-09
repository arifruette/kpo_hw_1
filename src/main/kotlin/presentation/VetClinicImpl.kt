package presentation

import domain.contract.VetClinic
import javax.inject.Inject

class VetClinicImpl @Inject constructor(): VetClinic {
    override fun checkAnimalHealth(healthLevel: Int): Boolean = healthLevel >= 5
}