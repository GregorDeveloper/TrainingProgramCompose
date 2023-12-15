package gregor.developer.trainingprogramcompose.dialog.dialog_date

sealed class DialogDateEvent {
    object OnCancel: DialogDateEvent()
    data class OnClickItem(val index: Int): DialogDateEvent()

}