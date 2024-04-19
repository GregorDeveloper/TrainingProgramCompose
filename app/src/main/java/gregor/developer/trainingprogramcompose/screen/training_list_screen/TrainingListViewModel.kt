package gregor.developer.trainingprogramcompose.screen.training_list_screen

import android.util.Log
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import gregor.developer.training_program_compose.data.repository.TrainingNameRepository
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.swipe_to_dismiss.ParameterSwipeItem
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.RoutesDialog
import gregor.developer.trainingprogramcompose.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch



import javax.inject.Inject

@HiltViewModel
class TrainingListViewModel @Inject constructor(
    private val repository: TrainingNameRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), DialogController {

    val list = repository.getAllItems()
    var date: String? = null


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var listItem:
            TrainingNameItem? = null
    var cancelSwipe = mutableStateOf(false)

    override var dialogTitle = mutableStateOf("List name")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    init {
        date = savedStateHandle.get<String>("date") ?: " "
        Log.d("LogDate", date ?: " date error")
    }
    fun onEvent(event: TrainingListEvent) {
        when (event) {
            is TrainingListEvent.OnItemSave -> {
                if(editableText.value.isEmpty())return
                viewModelScope.launch {
                    repository.insertItem(
                        TrainingNameItem(
                            listItem?.id,
                            editableText.value,
                            "day",
                            listItem?.numberOfList ?: 0
                        )
                    )
                }
            }

            is TrainingListEvent.OnShowEditDialog -> {
              //  choiceDialog.value = RoutesDialog.ADD_TRAINING
                listItem = event.item
                openDialog.value = true
                editableText.value = listItem?.name ?: ""
                dialogTitle.value = "Training name"
                showEditableText.value = true
            }

            is TrainingListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))

            }

            is TrainingListEvent.OnShowDeleteDialog -> {
                listItem = event.item
                openDialog.value = true
                dialogTitle.value = "Delete item?"
                showEditableText.value = false
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                cancelSwipe.value = true
            }

            is DialogEvent.OnConfirm -> {
                if (showEditableText.value) {
                    onEvent(TrainingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch {
                        listItem?.let { repository.deleteItem(it) }
                    }
                }
                cancelSwipe.value = true
                openDialog.value = false
            }

            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
            else -> {}
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}