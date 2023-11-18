package gregor.developer.trainingprogramcompose.dialog.dialog_description

import gregor.developer.trainingprogramcompose.data.static_data.WorkoutItem
import gregor.developer.trainingprogramcompose.dialog.DialogEvent

sealed class DialogDescriptionEvent {
    object OnCancel: DialogDescriptionEvent()
    data class OnConfirm(val item: String) : DialogDescriptionEvent()
}