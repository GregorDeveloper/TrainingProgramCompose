package gregor.developer.trainingprogramcompose.dialog

import androidx.compose.runtime.MutableState

interface DialogController {
    val dialogTitle: MutableState<String>
    val editableText: MutableState<String>
    val openDialog: MutableState<Boolean>
    val showEditableText: MutableState<Boolean>

    val choiceDialog: MutableState<String>

    fun onDialogEvent(event: DialogEvent)
}