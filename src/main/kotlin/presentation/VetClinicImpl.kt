package presentation

import domain.VetClinic
import javax.inject.Inject

class VetClinicImpl @Inject constructor(): VetClinic {
    override fun checkAnimalHealth(healthLevel: Int): Boolean = healthLevel >= 5
}