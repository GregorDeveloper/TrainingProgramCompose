package gregor.developer.trainingprogramcompose.utils

sealed class UiEvent{

    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    object BackStack: UiEvent()
    data class ShowDialog(val text: String): UiEvent()
    data class ShowToast(val date: String): UiEvent()
    data class ShowSnackBar(val message: String)
}
