package domain

/**
 * Нужен для автоинкремента инвентарного номера
 */
object InventoryValueHolder {
    private var _currentInventoryNumber: Long = 1

    val nextInventoryNumber: Long
        get() = _currentInventoryNumber++
}