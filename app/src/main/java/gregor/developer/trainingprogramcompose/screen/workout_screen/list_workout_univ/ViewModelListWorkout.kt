package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout_univ

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import gregor.developer.trainingprogramcompose.data.repository.WorkoutListTrainingRepository
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutDate
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DialogListController
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DialogListEvent
import gregor.developer.trainingprogramcompose.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelListWorkout @Inject constructor(
    private val repositoryWorkout: WorkOutListRepository,
    private val repositoryTraining: WorkoutListTrainingRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), DialogListController {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    var workoutListItem: WorkoutListItem? = null
    var listId: Int? = null

    override var list: MutableList<WorkoutDate> = mutableListOf()
        private set
    override var openDialog: MutableState<Boolean> = mutableStateOf(false)
        private set
    override var dateDialog: MutableState<String> = mutableStateOf("")
        private set

    var date: String? = null
    override var workoutOrFood: MutableState<Boolean> = mutableStateOf(true)



    val fabVisibility = mutableStateOf(false)
    val search = mutableStateOf("")

    init {
        listId = savedStateHandle.get<Int>("listId")
        date = savedStateHandle.get<String>("date")
        Log.d("LogInit", "listId = $listId")
        Log.d("LogInit", "date = ${date?.length}")
        Log.d("LogInit", "New")
    }

    fun onEvent(event: ListWorkoutEvent) {
        when (event) {
            is ListWorkoutEvent.SaveWorkout -> {
                list.add(
                    event.workout
                )
                openDialog.value = true
            }

            is ListWorkoutEvent.SaveList -> {
                if (list.size > 0) {
                    openDialog.value = true
                }

            }

            is ListWorkoutEvent.AddWorkoutList -> {
                if (event.workout.checking) {
                    list.add(
                        event.workout
                    )
                } else {

                    for ((index, value) in list.withIndex()) {
                        if (value.name.equals(event.workout.name)) {
                            list.removeAt(index)
                            break
                        }
                    }
                }
                checkFabPosition()
            }

            is ListWorkoutEvent.ClearList -> {
                if (list.size > 0) list.clear()
                checkFabPosition()
            }
        }
    }

    override fun onDialogEvent(event: DialogListEvent) {
        when (event) {
            is DialogListEvent.OnConfirm -> {
                viewModelScope.launch {
                    list.forEach {
                        saveItemWorkout(it.name)
                    }
                    openDialog.value = false
                    sendUiEvent(UiEvent.BackStack)
                }
            }

            is DialogListEvent.OnCancel -> {
                openDialog.value = false
                if(list.size == 1) list.clear()
            }
        }
    }

    private suspend fun saveItemWorkout(name: String) {
        if (listId == -1) {
            Log.d("LogSave", "listId == -1")
            repositoryWorkout.insertItem(
                WorkoutListItem(
                    id = workoutListItem?.id,
                    workoutName = name,
                    date = date ?: "",
                    listId = listId ?: -1
                )
            )
        } else if(listId != -1 && date == " ") {
            Log.d("LogSave", "listId != -1 && date == empty")
            repositoryTraining.insertItem(
                WorkoutListTraining(
                    id = workoutListItem?.id,
                    name = name,
                    listId = listId!!
                )
            )
        }else if(listId!! > 0){
            repositoryWorkout.insertItem(
                WorkoutListItem(
                    id = listId,
                    workoutName = name,
                    date = date ?: "",
                    listId = listId ?: -1
                )
            )
        }
    }

    private fun checkFabPosition() {
        fabVisibility.value = if (list.size > 0) true else false
    }

    private suspend fun sendUiEvent(event: UiEvent) {
        _uiEvent.send(event)
    }
}