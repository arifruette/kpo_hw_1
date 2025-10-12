package presentation.pages

/**
 * Тест на [FoodInfoPage]
 */
import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.contract.ReportBuilder
import domain.models.animals.Animal
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class FoodInfoPageTest {
    private val zooMockk = mockk<Zoo>()
    private val reportBuilderMockk = mockk<ReportBuilder> {
        every { build() } returns "test report"
    }
    private val consoleAgentMockk = mockk<ConsoleAgent> {
        justRun { showInfo("test report") }
    }

    private val page = FoodInfoPage(
        zoo = zooMockk,
        reportBuilder = reportBuilderMockk,
        consoleAgent = consoleAgentMockk
    )

    @Test
    fun testShowErrorNoAnimals() {
        every { zooMockk.animals } returns emptyList()
        justRun { consoleAgentMockk.showError(FoodInfoPage.NO_ANIMALS) } 

        page.render()

        verify(exactly = 1) { consoleAgentMockk.showError(FoodInfoPage.NO_ANIMALS) }
        verify(exactly = 0) { consoleAgentMockk.showInfo(any()) }
        verify(exactly = 0) { reportBuilderMockk.appendLine(any()) }
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsWithFoodArgsProvider::class)
    fun successReportBuild(
        animals: List<Animal>,
        expectedTotalFood: Int,
        expectedReportLines: List<String>
    ) {
        every { zooMockk.animals } returns animals
        justRun { consoleAgentMockk.showInfo(FoodInfoPage.REPORT_FOOD) } 
        expectedReportLines.forEach { line ->
            justRun { reportBuilderMockk.appendLine(line) } 
        }
        justRun { consoleAgentMockk.showInfo("Всего: ${expectedTotalFood}кг") } 

        page.render()

        verifySequence {
            consoleAgentMockk.showInfo(FoodInfoPage.REPORT_FOOD)
            consoleAgentMockk.showInfo("test report")
            consoleAgentMockk.showInfo("Всего: ${expectedTotalFood}кг")
        }
        expectedReportLines.forEach { line ->
            verify(exactly = 1) { reportBuilderMockk.appendLine(line) }
        }
        verify(exactly = 0) { consoleAgentMockk.showError(any()) }
    }

    @Test
    fun calculateFoodCorrectly() {
        val animals = listOf(
            mockk<Animal> {
                every { name } returns "test"
                every { number } returns 1
                every { food } returns 5
            },
            mockk<Animal> {
                every { name } returns "test"
                every { number } returns 2
                every { food } returns 7
            },
            mockk<Animal> {
                every { name } returns "test"
                every { number } returns 3
                every { food } returns 10
            }
        )

        every { zooMockk.animals } returns animals
        justRun { consoleAgentMockk.showInfo(FoodInfoPage.REPORT_FOOD) } 
        justRun { reportBuilderMockk.appendLine(any()) } 
        justRun { consoleAgentMockk.showInfo("Всего: 22кг") } 

        page.render()

        verify(exactly = 1) { consoleAgentMockk.showInfo("Всего: 22кг") }
        verify(exactly = 3) { reportBuilderMockk.appendLine(any()) }
    }

    @Test
    fun testCorrectOrder() {
        val animals = listOf(
            mockk<Animal> {
                every { name } returns "test"
                every { number } returns 1
                every { food } returns 3
            },
            mockk<Animal> {
                every { name } returns "test"
                every { number } returns 2
                every { food } returns 4
            }
        )

        every { zooMockk.animals } returns animals
        justRun { consoleAgentMockk.showInfo(FoodInfoPage.REPORT_FOOD) } 
        justRun { reportBuilderMockk.appendLine(any()) } 
        justRun { consoleAgentMockk.showInfo("Всего: 7кг") } 

        page.render()

        verifyOrder {
            reportBuilderMockk.appendLine("test с номером 1 потребляет 3кг еды")
            reportBuilderMockk.appendLine("test с номером 2 потребляет 4кг еды")
        }
    }

    private class AnimalsWithFoodArgsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    mockk<Animal> {
                        every { name } returns "test"
                        every { number } returns 1
                        every { food } returns 2
                    }
                ),
                2,
                listOf("test с номером 1 потребляет 2кг еды")
            ),
            Arguments.of(
                listOf(
                    mockk<Animal> {
                        every { name } returns "test"
                        every { number } returns 2
                        every { food } returns 3
                    },
                    mockk<Animal> {
                        every { name } returns "test"
                        every { number } returns 3
                        every { food } returns 15
                    }
                ),
                18,
                listOf(
                    "test с номером 2 потребляет 3кг еды",
                    "test с номером 3 потребляет 15кг еды"
                )
            )
        )
    }
}