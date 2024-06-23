package gregor.developer.trainingprogramcompose.screen.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import gregor.developer.training_program_compose.data.repository.TrainingNameRepository
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.dialog.RoutesDialog
import gregor.developer.trainingprogramcompose.utils.Routes
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: TrainingNameRepository
) : ViewModel(), DialogController {

    private val list = repository.getAllItems()

    override var addTraining = mutableStateOf(false)
        private set
    override var selectedMonthYear = mutableStateOf(false)
        private set
    override var listMonth = mutableStateOf(listOf(""))
        private set
    override var listYear  = mutableStateOf(listOf(""))
        private set
    override val indexMonth: MutableState<Int>
        get() = TODO("Not yet implemented")
    override val indexYear: MutableState<Int>
        get() = TODO("Not yet implemented")
    override var dialogTitle = mutableStateOf("Training name")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnItemSave -> {
                if(editableText.value.isEmpty()) return
                viewModelScope.launch {
                    repository.insertItem(
                        TrainingNameItem(
                            null,
                            editableText.value,
                            "day2",
                            if(list.first().isEmpty()){
                                0
                            }else {
                                repository.getLastElement().numberOfList + 1
                            }
                        )
                    )
                }
            }

            is MainScreenEvent.OnNewItemClick -> {
                openDialog.value = true
                  //  choiceDialog.value = RoutesDialog.ADD_TRAINING
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                editableText.value = ""
                openDialog.value = false
            }

            is DialogEvent.OnConfirm -> {
                onEvent(MainScreenEvent.OnItemSave)
                openDialog.value = false
                editableText.value = ""
            }

            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }

            is DialogEvent.AddList -> {

            }

            is DialogEvent.AddWorkout -> {

            }

            else -> {}
        }
    }
}