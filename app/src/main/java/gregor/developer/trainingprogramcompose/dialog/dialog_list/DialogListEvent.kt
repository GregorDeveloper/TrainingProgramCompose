package gregor.developer.trainingprogramcompose.dialog.dialog_list

sealed class DialogListEvent {
    object OnConfirm: DialogListEvent()
    object OnCancel: DialogListEvent()
}