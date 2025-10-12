package domain.contract

interface ReportBuilder {

    fun appendLine(message: String)

    fun append(message: String)

    fun build(): String

}