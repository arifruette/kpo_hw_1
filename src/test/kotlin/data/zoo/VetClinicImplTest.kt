package data.zoo

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class VetClinicImplTest {

    private val vetClinic = VetClinicImpl()

    @ParameterizedTest
    @ValueSource(ints = [5, 6, 7, 8, 9, 10])
    fun testApprove(healthLevel: Int) {
        assertTrue(vetClinic.checkAnimalHealth(healthLevel))
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4])
    fun testDisapprove(healthLevel: Int) {
        assertFalse(vetClinic.checkAnimalHealth(healthLevel))
    }
}