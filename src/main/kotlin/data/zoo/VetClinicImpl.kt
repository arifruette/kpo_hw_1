package data.zoo

import domain.contract.VetClinic
import javax.inject.Inject

/**
 * Реализация [VetClinic]
 */
class VetClinicImpl @Inject constructor(): VetClinic {
    override fun checkAnimalHealth(healthLevel: Int): Boolean = healthLevel >= 5
}