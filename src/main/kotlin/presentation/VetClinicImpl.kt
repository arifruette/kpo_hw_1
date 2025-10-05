package presentation

import domain.Animal
import domain.HealthStatus
import domain.VetClinic
import javax.inject.Inject

class VetClinicImpl @Inject constructor(): VetClinic {
    override fun checkAnimalHealth(animal: Animal): Boolean =
        animal.healthStatus == HealthStatus.HEALTHY
}