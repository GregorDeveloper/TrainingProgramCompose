package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

sealed class LastOrNextTrainingEvent {

    object LastTraining: LastOrNextTrainingEvent()

    object NextTraining: LastOrNextTrainingEvent()

    data class ClickDate(val date: String): LastOrNextTrainingEvent()

    data class ClickCurrentDate(val date: String): LastOrNextTrainingEvent()
}