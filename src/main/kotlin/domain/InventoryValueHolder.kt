package domain

object InventoryValueHolder {
    private var _currentInventoryNumber = 1

    val currentInventoryNumber: Int
        get() = _currentInventoryNumber.also { _currentInventoryNumber++ }
}