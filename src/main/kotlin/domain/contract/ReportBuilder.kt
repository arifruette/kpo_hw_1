package domain.contract

/**
 * Интерфейс билдера репортов
 */
interface ReportBuilder {

    fun appendLine(message: String)

    fun append(message: String)

    fun build(): String

}