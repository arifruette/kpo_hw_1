package data.zoo

import domain.contract.VetClinic
import domain.models.animals.Animal
import domain.models.inventory.Thing
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class ZooTest {
    private val vetClinicMockk = mockk<VetClinic>()
    private val zoo = Zoo(vetClinic = vetClinicMockk)

    @ParameterizedTest
    @ArgumentsSource(AnimalValidationArgsProvider::class)
    fun testAddAnimal(mockkHealth: Int, vetResult: Boolean, expected: Boolean) {
        val animal = mockk<Animal> { every { health } returns mockkHealth }
        every { vetClinicMockk.checkAnimalHealth(mockkHealth) } returns vetResult

        val result = zoo.addAnimal(animal)

        assertEquals(expected, result)
        if (expected) {
            assertTrue(zoo.animals.contains(animal))
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ThingsArgsProvider::class)
    fun testAddThing(things: List<Thing>) {
        things.forEach { zoo.addThing(it) }

        assertEquals(things.size, zoo.things.size)
        things.forEach { assertTrue(zoo.things.contains(it)) }
    }

    private class AnimalValidationArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(8, true, true),
            Arguments.of(3, false, false),
            Arguments.of(5, true, true),
            Arguments.of(4, false, false)
        )
    }

    private class ThingsArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(listOf(mockk<Thing>())),
            Arguments.of(listOf(mockk<Thing>(), mockk<Thing>())),
            Arguments.of(emptyList<Thing>())
        )
    }
}