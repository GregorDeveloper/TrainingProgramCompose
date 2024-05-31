package gregor.developer.trainingprogramcompose.screen.title_date

sealed class LastOrNextDateEvent {

    object LastTraining: LastOrNextDateEvent()

    object NextTraining: LastOrNextDateEvent()

    data class ClickDate(val date: String): LastOrNextDateEvent()

    data class ClickCurrentDate(val date: String): LastOrNextDateEvent()
}