package di.report

import dagger.Subcomponent
import domain.contract.ReportBuilder

@Subcomponent
interface ReportBuilderComponent {

    val reportBuilder: ReportBuilder

//    @Subcomponent.Factory
//    interface Factory {
//        fun create(): ReportBuilderComponent
//    }
}