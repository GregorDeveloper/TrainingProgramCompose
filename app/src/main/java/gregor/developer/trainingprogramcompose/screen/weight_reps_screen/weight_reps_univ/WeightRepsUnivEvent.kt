package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.data.entity.NoteItem

sealed class WeightRepsUnivEvent {

    object OpenDialog: WeightRepsUnivEvent()

    data class SaveWeightReps(val weightReps: WeightRepsWorkoutItem): WeightRepsUnivEvent()
    data class SaveNote(val note: String): WeightRepsUnivEvent()
    data class DeleteNote(val noteItem: NoteItem): WeightRepsUnivEvent()
    data class OpenDialogDescription(val workoutName: String): WeightRepsUnivEvent()
    object previousItemList: WeightRepsUnivEvent()
    object nextItemList: WeightRepsUnivEvent()
    object openDialogDate: WeightRepsUnivEvent()
}