package gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps

import gregor.developer.trainingprogramcompose.dialog.DialogEvent

sealed class DialogWeightRepsEvent{
    data class OnTextChange(val weight: String, val reps: String): DialogWeightRepsEvent()
    object OnCancel: DialogWeightRepsEvent()
    object OnConfirm: DialogWeightRepsEvent()
}
