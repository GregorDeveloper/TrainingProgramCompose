package gregor.developer.trainingprogramcompose.screen.workout_screen.user_workout

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import gregor.developer.trainingprogramcompose.data.repository.WorkoutListTrainingRepository
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.utils.UiEvent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserWorkoutScreenViewModel @Inject constructor(
    private val repositoryWorkoutListTraining: WorkoutListTrainingRepository,
    private val repositoryWorkoutList: WorkOutListRepository,
    savedStateHandle: SavedStateHandle,

    ) : ViewModel(), DialogController {

    var itemsList: Flow<List<WorkoutListTraining>>? = null
    var workoutListItem: WorkoutListTraining? = null
    var listId: Int? = null
    var date: String? = null
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        listId = savedStateHandle.get<Int>("listId")
        date = savedStateHandle.get<String>("date")
        Log.d("LogUserWorkout", "listId ${listId} \n date ${date}")
        itemsList = listId?.let { repositoryWorkoutListTraining.getAllItemsById(it) }
    }

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set




    fun onEvent(event: UserWorkoutEvent) {
        when (event) {
            is UserWorkoutEvent.OnSaveList -> {
                viewModelScope.launch{
                    itemsList?.collect { list ->
                        Log.d("ListSave", "List save")
                        for (i in 0..list.lastIndex) {
                            Log.d("ListSave", "List cycle")
                            repositoryWorkoutList.insertItem(
                                WorkoutListItem(
                                    null,
                                    list.get(i).name,
                                    date!!,
                                    0
                                )
                            )
                        }
                        sendUiEvent(UiEvent.Navigate(event.route))
                    }
                }

            }

            is UserWorkoutEvent.OnItemClick -> {

            }

            is UserWorkoutEvent.OnDelete -> {
                workoutListItem = event.item
                openDialog.value = true
                dialogTitle.value = "Delete item?"
                showEditableText.value = false
            }

            is UserWorkoutEvent.OnEditItem -> {

            }

            else -> {}
        }
    }


    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                // cancelSwipe.value = true
            }

            is DialogEvent.OnConfirm -> {
                if (showEditableText.value) {
                    // onEvent(TrainingListEvent.OnItemSave)

                } else {
                    viewModelScope.launch {
                        workoutListItem?.let { repositoryWorkoutListTraining.deleteItem(it) }
                    }
                }
                // cancelSwipe.value = true
                openDialog.value = false
            }

            else -> {

            }
        }
    }

    private suspend fun sendUiEvent(event: UiEvent) {
       // viewModelScope.launch {
            _uiEvent.send(event)
     //   }
    }
}