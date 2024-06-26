package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

sealed class WeightRepsUnivEvent {

    object OpenDialog: WeightRepsUnivEvent()

    object SaveWeightReps: WeightRepsUnivEvent()
    data class SaveNote(val note: String): WeightRepsUnivEvent()
    data class OpenDialogDescription(val workoutName: String): WeightRepsUnivEvent()
    object OpenDialogDate: WeightRepsUnivEvent()

    object OpenDeleteDialog: WeightRepsUnivEvent()
    object DeleteFullDay: WeightRepsUnivEvent()
    data class OnTextChangeNote(val note: String): WeightRepsUnivEvent()
}