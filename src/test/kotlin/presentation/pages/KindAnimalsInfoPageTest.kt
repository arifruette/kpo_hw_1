package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.contract.ReportBuilder
import domain.models.animals.Herbivore
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import presentation.pages.KindAnimalsInfoPage.Companion.KIND_ANIMALS_TITLE
import java.util.stream.Stream

internal class KindAnimalsInfoPageTest {
    private val zooMockk = mockk<Zoo>()
    private val consoleAgentMockk = mockk<ConsoleAgent>()
    private val reportBuilderMockk = mockk<ReportBuilder>()

    private val page = KindAnimalsInfoPage(
        zoo = zooMockk,
        consoleAgent = consoleAgentMockk,
        reportBuilder = reportBuilderMockk
    )

    @ParameterizedTest
    @ArgumentsSource(KindHerbivoresArgsProvider::class)
    fun successReport(herbivores: List<Herbivore>, expectedLines: List<String>) {
        every { zooMockk.animals } returns herbivores
        justRun { reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE) }
        expectedLines.forEach { line ->
            justRun { reportBuilderMockk.appendLine(line) }
        }
        every { reportBuilderMockk.build() } returns "test report"

        justRun { consoleAgentMockk.showInfo("test report") }
        
        page.render()

        verify(exactly = 1) { 
            reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE)
        }
        expectedLines.forEach { line ->
            verify(exactly = 1) { reportBuilderMockk.appendLine(line) }
        }
        verify(exactly = 1) { reportBuilderMockk.build() }
        verify(exactly = 1) { consoleAgentMockk.showInfo("test report") }
    }

    @Test
    fun noHerbivoresFound() {
        every { zooMockk.animals } returns emptyList()
        justRun { reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE) }
        justRun { reportBuilderMockk.appendLine(KindAnimalsInfoPage.NO_SUCH_ANIMALS) } 
        every { reportBuilderMockk.build() } returns "test report"

        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verifySequence {
            reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE)
            reportBuilderMockk.appendLine(KindAnimalsInfoPage.NO_SUCH_ANIMALS)
            reportBuilderMockk.build()
            consoleAgentMockk.showInfo("test report")
        }
    }

    @ParameterizedTest
    @ArgumentsSource(NotKindHerbivoresArgsProvider::class)
    fun filterLowKindnessAnimals(herbivores: List<Herbivore>) {
        every { zooMockk.animals } returns herbivores
        justRun { reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE) }
        every { reportBuilderMockk.build() } returns "test report"

        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verify(exactly = 1) { 
            reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE)
        }
        verify(exactly = 0) { 
            reportBuilderMockk.appendLine(match { it.contains(" с номером ") }) 
        }
        verify(exactly = 1) { reportBuilderMockk.build() }
        verify(exactly = 1) { consoleAgentMockk.showInfo("test report") }
    }

    @Test
    fun mixedAnimalsTest() {
        val herbivores = listOf(
            mockk<Herbivore> {
                every { kindness } returns 6
                every { name } returns "first"
                every { number } returns 1
            },
            mockk<Herbivore> {
                every { kindness } returns 7
                every { name } returns "second" 
                every { number } returns 2
            },
            mockk<Herbivore> {
                every { kindness } returns 8
                every { name } returns "third"
                every { number } returns 3
            }
        )

        every { zooMockk.animals } returns herbivores
        justRun { reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE) }
        justRun { reportBuilderMockk.appendLine("1. first с номером 1") } 
        justRun { reportBuilderMockk.appendLine("2. second с номером 2") } 
        justRun { reportBuilderMockk.appendLine("3. third с номером 3") } 
        every { reportBuilderMockk.build() } returns "test report"

        justRun { consoleAgentMockk.showInfo("test report") }

        page.render()

        verifySequence {
            reportBuilderMockk.appendLine(KIND_ANIMALS_TITLE)
            reportBuilderMockk.appendLine("1. first с номером 1")
            reportBuilderMockk.appendLine("2. second с номером 2")
            reportBuilderMockk.appendLine("3. third с номером 3")
            reportBuilderMockk.build()
            consoleAgentMockk.showInfo("test report")
        }
    }

    private class KindHerbivoresArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    mockk<Herbivore> {
                        every { kindness } returns 6
                        every { name } returns "herb"
                        every { number } returns 1
                    }
                ),
                listOf("1. herb с номером 1")
            ),
            Arguments.of(
                listOf(
                    mockk<Herbivore> {
                        every { kindness } returns 8
                        every { name } returns "rabbit"
                        every { number } returns 2
                    },
                    mockk<Herbivore> {
                        every { kindness } returns 9
                        every { name } returns "test"
                        every { number } returns 3
                    }
                ),
                listOf("1. rabbit с номером 2", "2. test с номером 3")
            ),
            Arguments.of(
                listOf(
                    mockk<Herbivore> {
                        every { kindness } returns 10
                        every { name } returns "kind"
                        every { number } returns 4
                    }
                ),
                listOf("1. kind с номером 4")
            )
        )
    }

    private class NotKindHerbivoresArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    mockk<Herbivore> {
                        every { kindness } returns 3
                        every { name } returns "test"
                        every { number } returns 1
                    }
                )
            ),
            Arguments.of(
                listOf(
                    mockk<Herbivore> {
                        every { kindness } returns 5
                        every { name } returns "test"
                        every { number } returns 2
                    }
                )
            ),
            Arguments.of(
                listOf(
                    mockk<Herbivore> { every { kindness } returns 1 },
                    mockk<Herbivore> { every { kindness } returns 2 },
                    mockk<Herbivore> { every { kindness } returns 4 }
                )
            )
        )
    }
}