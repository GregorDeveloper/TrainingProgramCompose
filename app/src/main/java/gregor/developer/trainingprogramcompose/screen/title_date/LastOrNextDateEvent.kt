package gregor.developer.trainingprogramcompose.screen.title_date

import java.time.Year

sealed class LastOrNextDateEvent {

    data class SelectedYearMonth(val year: Int, val month: String): LastOrNextDateEvent()
    data class OpenDropMenu(val title: String): LastOrNextDateEvent()
    object LastTraining: LastOrNextDateEvent()

    object NextTraining: LastOrNextDateEvent()

    data class ClickDate(val date: String): LastOrNextDateEvent()

    data class ClickCurrentDate(val date: String): LastOrNextDateEvent()
}