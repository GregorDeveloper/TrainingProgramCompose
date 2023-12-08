package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.dialog.DialogEvent

sealed class NewWeightRepsEvent{

    object OnItemSave: NewWeightRepsEvent()
    data class OnShowEditDialog(val index: Int): NewWeightRepsEvent()
    data class OnDeleteDialog(val index: Int): NewWeightRepsEvent()
    object OnDeleteItem: NewWeightRepsEvent()
    object OnShowDialog: NewWeightRepsEvent()
//    object OnCancel: NewWeightRepsEvent()
//    object OnConfirm: NewWeightRepsEvent()
}
