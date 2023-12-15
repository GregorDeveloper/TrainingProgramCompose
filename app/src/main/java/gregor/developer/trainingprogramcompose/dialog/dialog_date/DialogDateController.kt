package gregor.developer.trainingprogramcompose.dialog.dialog_date

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

interface DialogDateController {

    val openDialog: MutableState<Boolean>
    val listDate: MutableList<String>

    fun onDialogDateEvent(event: DialogDateEvent)
}