package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.dialog.DialogEvent

sealed class NewWeightRepsEvent{

    data class OnItemSave(val weight: String, val reps: String): NewWeightRepsEvent()
    object OnShowDialog: NewWeightRepsEvent()
    object OnCancel: NewWeightRepsEvent()
    object OnConfirm: NewWeightRepsEvent()
}
