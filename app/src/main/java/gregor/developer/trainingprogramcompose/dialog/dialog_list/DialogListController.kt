package gregor.developer.trainingprogramcompose.dialog.dialog_list

import androidx.compose.runtime.MutableState
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutDate

interface DialogListController {
    val list: MutableList<WorkoutDate>
    val openDialog: MutableState<Boolean>
    val dateDialog: MutableState<String>
    val workoutOrFood: MutableState<Boolean>

    fun onDialogEvent(event: DialogListEvent)
}