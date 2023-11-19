package gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps

import androidx.compose.runtime.MutableState


interface DialogWeightRepsController {
    val openDialog: MutableState<Boolean>
    val weight: MutableState<String>
    val reps: MutableState<String>
    fun onDialogEvent(event: DialogWeightRepsEvent)
}