package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.contract.ReportBuilder
import domain.models.animals.Animal
import domain.models.inventory.Thing
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class RevisionPageTest {
    private val consoleAgentMockk = mockk<ConsoleAgent>()
    private val reportBuilderMockk = mockk<ReportBuilder>()
    private val zooMockk = mockk<Zoo>()

    private val page = RevisionPage(
        consoleAgent = consoleAgentMockk,
        reportBuilder = reportBuilderMockk,
        zoo = zooMockk
    )

    @Test
    fun testShowError() {
        every { zooMockk.animals } returns emptyList()
        every { zooMockk.things } returns emptyList()
        justRun { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        justRun { consoleAgentMockk.showError(RevisionPage.NO_THINGS) }

        page.render()

        verifySequence {
            consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT)
            consoleAgentMockk.showError(RevisionPage.NO_THINGS)
        }
        verify(exactly = 0) { reportBuilderMockk.appendLine(any()) }
        verify(exactly = 0) { reportBuilderMockk.build() }
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsOnlyArgsProvider::class)
    fun testOnlyAnimals(
        animals: List<Animal>,
        expectedLines: List<String>
    ) {
        every { zooMockk.animals } returns animals
        every { zooMockk.things } returns emptyList()
        justRun { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        expectedLines.forEach { line ->
            justRun { reportBuilderMockk.appendLine(line) }
        }
        every { reportBuilderMockk.build() } returns "test report"
        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verify(exactly = 1) { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        expectedLines.forEach { line ->
            verify(exactly = 1) { reportBuilderMockk.appendLine(line) }
        }
        verify(exactly = 1) { reportBuilderMockk.build() }
        verify(exactly = 1) { consoleAgentMockk.showInfo("test report") }
        verify(exactly = 0) { consoleAgentMockk.showError(any()) }
    }

    @ParameterizedTest
    @ArgumentsSource(ThingsOnlyArgsProvider::class)
    fun testOnlyThings(things: List<Thing>, expectedLines: List<String>) {
        every { zooMockk.animals } returns emptyList()
        every { zooMockk.things } returns things
        justRun { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        expectedLines.forEach { line ->
            justRun { reportBuilderMockk.appendLine(line) }
        }
        every { reportBuilderMockk.build() } returns "test report"
        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verify(exactly = 1) { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        expectedLines.forEach { line ->
            verify(exactly = 1) { reportBuilderMockk.appendLine(line) }
        }
        verify(exactly = 1) { reportBuilderMockk.build() }
        verify(exactly = 1) { consoleAgentMockk.showInfo("test report") }
        verify(exactly = 0) { consoleAgentMockk.showError(any()) }
    }

    @ParameterizedTest
    @ArgumentsSource(MixedInventoryArgsProvider::class)
    fun testAllReport(
        animals: List<Animal>,
        things: List<Thing>,
        expectedLines: List<String>
    ) {
        every { zooMockk.animals } returns animals
        every { zooMockk.things } returns things
        justRun { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        expectedLines.forEach { line ->
            justRun { reportBuilderMockk.appendLine(line) }
        }
        every { reportBuilderMockk.build() } returns "test report"
        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verify(exactly = 1) { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        expectedLines.forEach { line ->
            verify(exactly = 1) { reportBuilderMockk.appendLine(line) }
        }
        verify(exactly = 1) { reportBuilderMockk.build() }
        verify(exactly = 1) { consoleAgentMockk.showInfo("test report") }
        verify(exactly = 0) { consoleAgentMockk.showError(any()) }
    }

    @Test
    fun correctNumbersTestIds() {
        val animals = listOf(
            mockk<Animal> {
                every { name } returns "test"
                every { number } returns 1
                every { type } returns mockk {
                    every { animalName } returns "Лев"
                }
            },
            mockk<Animal> {
                every { name } returns "test"
                every { number } returns 2
                every { type } returns mockk {
                    every { animalName } returns "Тигр"
                }
            }
        )
        val things = listOf(
            mockk<Thing> {
                every { number } returns 101
                every { type } returns mockk {
                    every { thingName } returns "Стол"
                }
            },
            mockk<Thing> {
                every { number } returns 102
                every { type } returns mockk {
                    every { thingName } returns "Компьютер"
                }
            }
        )

        every { zooMockk.animals } returns animals
        every { zooMockk.things } returns things
        justRun { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        justRun { reportBuilderMockk.appendLine(any()) }
        every { reportBuilderMockk.build() } returns "test report"
        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verifyOrder {
            reportBuilderMockk.appendLine("1. Животное test(Лев) с номером 1")
            reportBuilderMockk.appendLine("2. Животное test(Тигр) с номером 2")
            reportBuilderMockk.appendLine("3. Предмет Стол с номером 101")
            reportBuilderMockk.appendLine("4. Предмет Компьютер с номером 102")
            reportBuilderMockk.appendLine("Всего животных: 2")
            reportBuilderMockk.appendLine("Всего вещей: 2")
        }
    }

    @Test
    fun correctTotalTest() {
        val animals = listOf(
            mockk<Animal> {
                every { name } returns "Animal1"
                every { number } returns 1
                every { type } returns mockk { every { animalName } returns "test1" }
            },
            mockk<Animal> {
                every { name } returns "Animal2"
                every { number } returns 2
                every { type } returns mockk { every { animalName } returns "test2" }
            }
        )
        val things = listOf(
            mockk<Thing> {
                every { number } returns 1
                every { type } returns mockk { every { thingName } returns "test2" }
            }
        )

        every { zooMockk.animals } returns animals
        every { zooMockk.things } returns things
        justRun { consoleAgentMockk.showInfo(RevisionPage.INVENT_REPORT) }
        justRun { reportBuilderMockk.appendLine(any()) }
        every { reportBuilderMockk.build() } returns "test report"
        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verify {
            reportBuilderMockk.appendLine("Всего животных: 2")
            reportBuilderMockk.appendLine("Всего вещей: 1")
        }
    }

    private class AnimalsOnlyArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    mockk<Animal> {
                        every { name } returns "test"
                        every { number } returns 1
                        every { type } returns mockk {
                            every { animalName } returns "Кролик"
                        }
                    }
                ),
                listOf(
                    "1. Животное test(Кролик) с номером 1",
                    "Всего животных: 1",
                    "Всего вещей: 0"
                )
            ),
            Arguments.of(
                listOf(
                    mockk<Animal> {
                        every { name } returns "test"
                        every { number } returns 1
                        every { type } returns mockk {
                            every { animalName } returns "test2"
                        }
                    },
                    mockk<Animal> {
                        every { name } returns "test"
                        every { number } returns 2
                        every { type } returns mockk {
                            every { animalName } returns "test2"
                        }
                    }
                ),
                listOf(
                    "1. Животное test(test2) с номером 1",
                    "2. Животное test(test2) с номером 2",
                    "Всего животных: 2",
                    "Всего вещей: 0"
                )
            )
        )
    }

    private class ThingsOnlyArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    mockk<Thing> {
                        every { number } returns 1
                        every { type } returns mockk {
                            every { thingName } returns "Стол"
                        }
                    }
                ),
                listOf(
                    "1. Предмет Стол с номером 1",
                    "Всего животных: 0",
                    "Всего вещей: 1"
                )
            ),
            Arguments.of(
                listOf(
                    mockk<Thing> {
                        every { number } returns 101
                        every { type } returns mockk {
                            every { thingName } returns "Стол"
                        }
                    },
                    mockk<Thing> {
                        every { number } returns 102
                        every { type } returns mockk {
                            every { thingName } returns "Компьютер"
                        }
                    }
                ),
                listOf(
                    "1. Предмет Стол с номером 101",
                    "2. Предмет Компьютер с номером 102",
                    "Всего животных: 0",
                    "Всего вещей: 2"
                )
            )
        )
    }

    private class MixedInventoryArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    mockk<Animal> {
                        every { name } returns "test"
                        every { number } returns 1
                        every { type } returns mockk {
                            every { animalName } returns "test"
                        }
                    }
                ),
                listOf(
                    mockk<Thing> {
                        every { number } returns 101
                        every { type } returns mockk {
                            every { thingName } returns "Стол"
                        }
                    }
                ),
                listOf(
                    "1. Животное test(test) с номером 1",
                    "2. Предмет Стол с номером 101",
                    "Всего животных: 1",
                    "Всего вещей: 1"
                )
            )
        )
    }
}