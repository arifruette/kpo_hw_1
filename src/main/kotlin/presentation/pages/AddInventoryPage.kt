package presentation.pages

import data.zoo.Zoo
import domain.contract.ConsoleAgent
import domain.models.inventory.Thing
import domain.models.inventory.types.Computer
import domain.models.inventory.types.Table
import domain.models.inventory.types.ThingType
import presentation.Page
import javax.inject.Inject

class AddInventoryPage @Inject constructor(
    private val zoo: Zoo,
    override val consoleAgent: ConsoleAgent
) : Page() {
    override fun render() {
        consoleAgent.showInfo(ThingType.typesList)
        val enteredType = consoleAgent.promptInteger(THING_TYPE)
        val thingType = ThingType.entries.firstOrNull {
            it.ordinal + 1 == enteredType
        }
        if (thingType == null) {
            consoleAgent.showError(NO_SUCH_TYPE)
            return
        }
        val newThing = createThingByType(thingType)
        zoo.addThing(newThing)
        consoleAgent.showInfo("Вещь ${thingType.thingName} с номером ${newThing.number} создана")
    }

    private fun createThingByType(thingType: ThingType): Thing = when (thingType) {
        ThingType.TABLE -> Table()
        ThingType.COMPUTER -> Computer()
    }

    companion object {
        const val THING_TYPE = "Введите тип вещи: "
        const val NO_SUCH_TYPE = "Такого типа не существует"
    }
}