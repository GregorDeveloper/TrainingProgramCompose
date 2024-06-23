package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

sealed class WeightRepsUnivEvent {

    data class OpenDialog(val dialogTitle: String): WeightRepsUnivEvent()

    object SaveWeightReps: WeightRepsUnivEvent()
    data class SaveNote(val note: String): WeightRepsUnivEvent()
    data class OpenDialogDescription(val workoutName: String): WeightRepsUnivEvent()

    data class OnShowEditDialog(val index: Int, val dialogTitle: String): WeightRepsUnivEvent()
    data class OpenDeleteDialog(val index: Int, val dialogTitle: String): WeightRepsUnivEvent()

    object DeleteFullDay: WeightRepsUnivEvent()

    object DeleteNumber: WeightRepsUnivEvent()
    object OpenDialogDate: WeightRepsUnivEvent()
    data class OpenDeleteFullDayDialog(val dialogTitle: String): WeightRepsUnivEvent()
    data class OnTextChangeNote(val note: String): WeightRepsUnivEvent()
}