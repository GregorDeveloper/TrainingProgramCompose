package gregor.developer.trainingprogramcompose.screen.workout_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.static_data.MuscleList
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutItem
import gregor.developer.trainingprogramcompose.dialog.dialog_description.DialogDescriptionController
import gregor.developer.trainingprogramcompose.dialog.dialog_description.DialogDescriptionEvent
import gregor.developer.trainingprogramcompose.utils.StringResourcesProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkScreenViewModel @Inject constructor(
    private val repository: WorkOutListRepository,
    savedStateHandle: SavedStateHandle,
    private val stringResProv: StringResourcesProvider
) : ViewModel(), DialogDescriptionController {

    var itemsList: Flow<List<WorkoutListItem>>? = null
    var workoutListItem: WorkoutListItem? = null
    var listId: Int? = null
    var itemId: Int? = null
    val searchWorkout = mutableStateOf("")
    init {
        listId = savedStateHandle.get<Int>("listId")
        itemId = savedStateHandle.get<Int>("itemId") ?: -1
        Log.d("MyLog", "list Id: $listId " + "item Id: $itemId")
        itemsList = listId?.let { repository.getAllItemsById(it) }

    }

    override var workoutImage = mutableStateOf(0)
        private set
    override var workoutName = mutableStateOf("")
        private set
    override var workoutDescription = mutableStateOf("")
        private set


    override var openDialogDescription = mutableStateOf(false)
        private set
    override var addWorkoutItem = mutableStateOf(false)
        private set

    val muscleGroup = MuscleList()
    val itemGroup = mutableStateOf<List<WorkoutItem>>(muscleGroup.workoutNeck)


    fun onEvent(event: WorkoutEvent) {

        when (event) {
            is WorkoutEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == null) return@launch
                    repository.insertItem(
                        WorkoutListItem(
                            workoutListItem?.id,
                            stringResProv.getString(event.item.name),
                            listId!!,
                            0
                        )
                    )
                    workoutListItem = null
                }

            }

            is WorkoutEvent.OnItemEdit -> {
                workoutListItem = event.item
            }

            is WorkoutEvent.OnShowDescriptionDialog -> {
                openDialogDescription.value = true
                workoutImage.value = event.item.imageId[0]
                workoutName.value = stringResProv.getString(event.item.name)

                if (listId != null) {
                    addWorkoutItem.value = true
                }
            }

            is WorkoutEvent.OnDelete -> {

            }

            is WorkoutEvent.OnItemClick -> {

            }

            is WorkoutEvent.OnSearchWorkout -> {

            }
        }
    }

    override fun onDialogDescriptionEvent(event: DialogDescriptionEvent) {

        when (event) {
            is DialogDescriptionEvent.OnCancel -> {
                openDialogDescription.value = false
            }

            is DialogDescriptionEvent.OnConfirm -> {
                openDialogDescription.value = false
            }
        }
    }

    fun getListWorkOut(group: String) {
        when (group) {
            "Neck" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutNeck
            }

            "Traps" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutTraps
            }

            "Shoulders" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutShoulders
            }

            "Chest" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutChest
            }

            "Lats" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutLats
            }

            "Biceps" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutBiceps
            }

            "Triceps" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutTriceps
            }

            "Forearms" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutForearms
            }

            "Abdominals" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutAbdominals
            }

            "Middle back" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutMiddleBack
            }

            "Lower back" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutLowerBack
            }

            "Glutes" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutGlutes
            }

            "Abductors" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutAbductors
            }

            "Quadriceps" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutQuadriceps
            }

            "Adductors" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutAdductors
            }

            "Hamstrings" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutHamstrings
            }

            "Calves" -> {
                itemGroup.value.isEmpty()
                itemGroup.value = muscleGroup.workoutCalves
            }
        }
    }


}