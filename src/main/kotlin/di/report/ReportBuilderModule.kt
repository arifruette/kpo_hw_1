package di.report

import dagger.Binds
import dagger.Module
import data.report.DefaultReportBuilder
import domain.contract.ReportBuilder

@Module
interface ReportBuilderModule {

    @Binds
    fun bindDefaultReportBuilder(reportBuilderImpl: DefaultReportBuilder): ReportBuilder
}