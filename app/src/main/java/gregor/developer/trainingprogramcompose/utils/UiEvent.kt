package gregor.developer.trainingprogramcompose.utils

sealed class UiEvent{

    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()

    data class ShowToast(val date: String): UiEvent()
    data class ShowSnackBar(val message: String)
}
