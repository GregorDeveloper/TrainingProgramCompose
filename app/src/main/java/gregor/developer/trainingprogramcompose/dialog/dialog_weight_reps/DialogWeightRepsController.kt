package gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps

import androidx.compose.runtime.MutableState


interface DialogWeightRepsController {
    val dialogTitle: MutableState<String>
    val openDialog: MutableState<Boolean>
    val weight: MutableState<String>
    val reps: MutableState<String>
    val edit: MutableState<Boolean>
    val editIndexItem: MutableState<Int>
    val showEditText: MutableState<Boolean>
    fun onDialogEvent(event: DialogWeightRepsEvent)
}