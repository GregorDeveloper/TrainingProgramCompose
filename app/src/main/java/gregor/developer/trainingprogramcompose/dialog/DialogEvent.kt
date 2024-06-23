package gregor.developer.trainingprogramcompose.dialog

sealed class DialogEvent{

    data class AddWorkout(val route: String): DialogEvent()

    data class AddList(val route: String): DialogEvent()
    data class OnTextChange(val text: String): DialogEvent()
    object OnReturnCurrentMonth: DialogEvent()
    data class choseMonthYear(val year: String, val month: String): DialogEvent()
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
}


