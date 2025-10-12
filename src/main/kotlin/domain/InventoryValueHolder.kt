package domain

object InventoryValueHolder {
    private var _currentInventoryNumber = 1

    val nextInventoryNumber: Int
        get() = _currentInventoryNumber++
}