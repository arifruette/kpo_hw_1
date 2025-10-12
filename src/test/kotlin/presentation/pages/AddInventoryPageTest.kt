package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.models.inventory.types.Computer
import domain.models.inventory.types.Table
import domain.models.inventory.types.ThingType
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.reflect.KClass

/**
 * Тест на [AddInventoryPage]
 */
internal class AddInventoryPageTest {
    private val zooMockk = mockk<Zoo>()
    private val consoleAgentMockk = mockk<ConsoleAgent>()

    private val page = AddInventoryPage(zoo = zooMockk, consoleAgent = consoleAgentMockk)

    @ParameterizedTest
    @ArgumentsSource(ValidThingTypeArgsProvider::class)
    fun testSuccessCreation(typeOrdinal: Int, expectedThingClass: KClass<*>) {
        justRun { consoleAgentMockk.showInfo(ThingType.typesList) } 
        every { consoleAgentMockk.promptInteger(AddInventoryPage.THING_TYPE) } returns typeOrdinal
        justRun { zooMockk.addThing(any()) } 
        justRun { consoleAgentMockk.showInfo(any()) } 

        page.render()

        verify(exactly = 1) {
            zooMockk.addThing(match { thing ->
                thing::class == expectedThingClass
            })
        }
        verify {
            consoleAgentMockk.showInfo(any())
        }
        verify(exactly = 0) { consoleAgentMockk.showError(any()) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 3, -1, 999])
    fun testShowErrorIfNotValidType(invalidType: Int) {
        justRun { consoleAgentMockk.showInfo(ThingType.typesList) } 
        every { consoleAgentMockk.promptInteger(AddInventoryPage.THING_TYPE) } returns invalidType
        justRun { consoleAgentMockk.showError(AddInventoryPage.NO_SUCH_TYPE) } 

        page.render()

        verify(exactly = 0) { zooMockk.addThing(any()) }
        verify(exactly = 1) { consoleAgentMockk.showError(AddInventoryPage.NO_SUCH_TYPE) }
    }

    private class ValidThingTypeArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(1, Table::class),
            Arguments.of(2, Computer::class)
        )
    }
}