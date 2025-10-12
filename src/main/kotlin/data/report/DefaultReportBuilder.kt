package data.report

import domain.contract.ReportBuilder
import javax.inject.Inject

class DefaultReportBuilder @Inject constructor():  ReportBuilder {

    private val reportString = StringBuilder()

    override fun appendLine(message: String) {
        reportString.appendLine(message)
    }

    override fun append(message: String) {
        reportString.append(message)
    }

    override fun build(): String = reportString.toString().also { reportString.clear() }
}