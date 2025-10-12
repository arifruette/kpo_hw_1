package presentation.pages

import domain.Zoo
import domain.contract.ConsoleAgent
import domain.models.types.Monkey
import domain.models.types.Rabbit
import domain.models.types.Tiger
import domain.models.types.Wolf
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource
import presentation.pages.AddAnimalPage.Companion.ANIMAL_TYPE
import presentation.pages.AddAnimalPage.Companion.FOOD_LEVEL
import presentation.pages.AddAnimalPage.Companion.HEALTH_LEVEL
import presentation.pages.AddAnimalPage.Companion.INCORRECT_DATA
import presentation.pages.AddAnimalPage.Companion.KINDNESS_LEVEL
import presentation.pages.AddAnimalPage.Companion.NOT_PASSED
import presentation.pages.AddAnimalPage.Companion.NO_SUCH_TYPE
import java.util.stream.Stream
import kotlin.reflect.KClass

/**
 * Тест на [AddAnimalPageTest]
 */
internal class AddAnimalPageTest {
    private val zooMockk = mockk<Zoo>()

    private val consoleAgentMockk = mockk<ConsoleAgent>()

    private val page = AddAnimalPage(zoo = zooMockk, consoleAgent = consoleAgentMockk)

    @ParameterizedTest
    @ArgumentsSource(SuccessCreationArgsProvider::class)
    fun successAnimalCreation(
        typeOrdinal: Int,
        expectedClass: KClass<*>
    ) {
        val name = "test"
        val health = 8
        val food = 5
        val kindness = 7

        every { zooMockk.addAnimal(any()) } returns true

        every { consoleAgentMockk.prompt(any(), any()) } returns name
        every { consoleAgentMockk.promptInteger(HEALTH_LEVEL) } returns health
        every { consoleAgentMockk.promptInteger(ANIMAL_TYPE) } returns typeOrdinal
        every { consoleAgentMockk.promptInteger(FOOD_LEVEL) } returns food

        every { consoleAgentMockk.promptInteger(KINDNESS_LEVEL) } returns kindness

        justRun { consoleAgentMockk.showInfo(any<String>()) }

        page.render()

        verify(exactly = 1) {
            zooMockk.addAnimal(match { animal ->
                animal::class == expectedClass &&
                        animal.name == name &&
                        animal.health == health &&
                        animal.food == food
            })
        }
        verify {
            consoleAgentMockk.showInfo(any())
        }
    }

    private class SuccessCreationArgsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(1, Monkey::class),
            Arguments.of(2, Rabbit::class),
            Arguments.of(3, Tiger::class),
            Arguments.of(4, Wolf::class)
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 11, 15, 100])
    fun healthOutOfRangeError(invalidHealth: Int) {
        every { consoleAgentMockk.prompt(any(), any()) } returns "test"
        every { consoleAgentMockk.promptInteger(HEALTH_LEVEL) } returns invalidHealth
        justRun { consoleAgentMockk.showError(INCORRECT_DATA) }
        justRun { consoleAgentMockk.showInfo(any<String>()) }
        justRun { consoleAgentMockk.promptInteger(ANIMAL_TYPE, any()) }

        page.render()

        verify(exactly = 0) { zooMockk.addAnimal(any()) }
        verify { consoleAgentMockk.showError(INCORRECT_DATA) }
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidTypeArgsProvider::class)
    fun showErrorForInvalidAnimalType(invalidType: Int) {

        every { consoleAgentMockk.prompt(any(), any()) } returns "test"
        every { consoleAgentMockk.promptInteger(HEALTH_LEVEL) } returns 4
        every { consoleAgentMockk.promptInteger(ANIMAL_TYPE) } returns invalidType
        justRun { consoleAgentMockk.showInfo(any()) }
        justRun { consoleAgentMockk.showError(NO_SUCH_TYPE) }

        page.render()

        verify(exactly = 0) { zooMockk.addAnimal(any()) }
        verify { consoleAgentMockk.showError(NO_SUCH_TYPE) }
    }

    private class InvalidTypeArgsProvider : ArgumentsProvider {

        override fun provideArguments(p0: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(0),
            Arguments.of(5),
            Arguments.of(-1),
            Arguments.of(999)
        )
    }

    @Test
    fun testNoCreationIfNotPass() {
        every { consoleAgentMockk.prompt(any(), any()) } returns "test"
        every { consoleAgentMockk.promptInteger(HEALTH_LEVEL) } returns 0
        every { consoleAgentMockk.promptInteger(FOOD_LEVEL) } returns 3
        every { consoleAgentMockk.promptInteger(ANIMAL_TYPE) } returns 3
        justRun { consoleAgentMockk.showInfo(any()) }
        justRun { consoleAgentMockk.showError(NOT_PASSED) }

        every { zooMockk.addAnimal(any()) } returns false
        page.render()

        verify(exactly = 1) { zooMockk.addAnimal(any()) }
        verify(exactly = 1) { consoleAgentMockk.showError(NOT_PASSED) }
    }
}