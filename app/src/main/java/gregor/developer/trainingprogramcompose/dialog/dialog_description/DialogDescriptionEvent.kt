package gregor.developer.trainingprogramcompose.dialog.dialog_description

import gregor.developer.trainingprogramcompose.dialog.DialogEvent

sealed class DialogDescriptionEvent {
    object OnCancel: DialogDescriptionEvent()
    object OnConfirm: DialogDescriptionEvent()
}