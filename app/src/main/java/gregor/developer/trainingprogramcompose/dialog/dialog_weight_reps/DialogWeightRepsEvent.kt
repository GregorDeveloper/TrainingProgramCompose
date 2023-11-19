package gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps

import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen.NewWeightRepsEvent

sealed class DialogWeightRepsEvent{
    data class OnTextChangeWeight(val weight: String): DialogWeightRepsEvent()
    data class OnTextChangeReps(val reps: String): DialogWeightRepsEvent()
    object OnCancel: DialogWeightRepsEvent()
    object OnConfirm: DialogWeightRepsEvent()
}
