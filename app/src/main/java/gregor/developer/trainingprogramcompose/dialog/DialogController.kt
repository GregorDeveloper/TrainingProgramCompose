package gregor.developer.trainingprogramcompose.dialog

import androidx.compose.runtime.MutableState

interface DialogController {
    val addTraining: MutableState<Boolean>
    val selectedMonthYear: MutableState<Boolean>
    val listMonth: MutableState<List<String>>
    val listYear: MutableState<List<String>>
    val indexMonth: MutableState<Int>
    val indexYear: MutableState<Int>

    val dialogTitle: MutableState<String>
    val editableText: MutableState<String>
    val openDialog: MutableState<Boolean>
    val showEditableText: MutableState<Boolean>

    fun onDialogEvent(event: DialogEvent)
}