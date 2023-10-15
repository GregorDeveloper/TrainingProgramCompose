package gregor.developer.trainingprogramcompose.dialog.dialog_description

import androidx.compose.runtime.MutableState

interface DialogDescriptionController {

    val workoutImage: MutableState<Int>
    val workoutName: MutableState<String>
    val workoutDescription: MutableState<String>

    val openDialogDescription: MutableState<Boolean>
    val addWorkoutItem: MutableState<Boolean>
    fun onDialogDescriptionEvent(event: DialogDescriptionEvent)

}