package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.static_data.MuscleItem
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
    private val stringResProv: StringResourcesProvider,
) : ViewModel(), DialogDescriptionController {

    var itemsList: Flow<List<WorkoutListItem>>? = null
    var workoutListItem: WorkoutListItem? = null
    var listId: Int? = null
    var date: String? = null
    val searchWorkout = mutableStateOf("")
    private var groupMuscleSave: String = "Neck"


    init {
        listId = savedStateHandle.get<Int>("listId")
        date = savedStateHandle.get<String>("date") ?: ""
        Log.d("LogDate", date ?: "null")
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
    val listSelected = mutableStateOf<List<WorkoutItem>>(muscleGroup.workoutNeck)
    val groupList = mutableStateOf<List<MuscleItem>>(muscleGroup.muscleGroupList)


    fun onEvent(event: WorkoutEvent) {
        when (event) {
            is WorkoutEvent.OnItemSave -> {
//                viewModelScope.launch {
//                    if (listId.equals("")) return@launch
//                    repository.insertItem(
//                        WorkoutListItem(
//                            workoutListItem?.id,
//                            event.item,
//                            "listId!!",
//                            0
//                        )
//                    )
//                    workoutListItem = null
//
//                }

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
                Log.d("MyLog", "Event")
                filterGroupList(searchWorkout.value)
            }

        }
    }


    override fun onDialogDescriptionEvent(event: DialogDescriptionEvent) {
        when (event) {
            is DialogDescriptionEvent.OnCancel -> {
                openDialogDescription.value = false

            }

            is DialogDescriptionEvent.OnConfirm -> {
                onEvent(WorkoutEvent.OnItemSave(event.item))
                openDialogDescription.value = false

            }
        }
    }

//    fun filterWorkoutList(search: String): List<WorkoutItem> {
//        if (search.isEmpty()) {
//            getListWorkOut(groupMuscleSave)
//            groupList.value = muscleGroup.muscleGroupList
//        } else {
//            filterGroupList(search)
//        }
//        Log.d("MyLog", "filterWorkoutList")
//        return itemGroup.value
//    }


    private fun filterGroupList(search: String) {
        viewModelScope.launch {
            val listWorkout = listOf<List<WorkoutItem>>(
                muscleGroup.workoutNeck,
                muscleGroup.workoutTraps,
                muscleGroup.workoutShoulders,
                muscleGroup.workoutChest,
                muscleGroup.workoutLats,
                muscleGroup.workoutBiceps,
                muscleGroup.workoutTriceps,
                muscleGroup.workoutForearms,
                muscleGroup.workoutAbdominals,
                muscleGroup.workoutMiddleBack,
                muscleGroup.workoutLowerBack,
                muscleGroup.workoutGlutes,
                muscleGroup.workoutAbductors,
                muscleGroup.workoutQuadriceps,
                muscleGroup.workoutAdductors,
                muscleGroup.workoutHamstrings,
                muscleGroup.workoutCalves,
            )
            val editListWorkout: MutableList<WorkoutItem> = emptyList<WorkoutItem>().toMutableList()
            val editListGroup: MutableSet<MuscleItem> = emptyList<MuscleItem>().toMutableSet()
            val editListTest: MutableSet<String> = emptyList<String>().toMutableSet()
            for (i in 0..listWorkout.size - 1) {
                for (liNew in listWorkout.get(i)) {
                    if (stringResProv.getString(liNew.name).lowercase()
                            .contains(search.lowercase())
                    ) {
                        if (listWorkout.get(i) == listSelected.value) {
                            editListWorkout.add(liNew)
                        }
                        editListGroup.add(muscleGroup.muscleGroupList.get(i))
                    }
                }
                editListTest.add(listWorkout.get(i).toString())
            }
            itemGroup.value = editListWorkout
            groupList.value = editListGroup.toList()
        }
    }

    fun getListWorkOut(group: String): List<WorkoutItem> {
        when (group) {
            "Neck" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutNeck
            }

            "Traps" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutTraps
            }

            "Shoulders" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutShoulders
            }

            "Chest" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutChest
            }

            "Lats" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutLats
            }

            "Biceps" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutBiceps
            }

            "Triceps" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutTriceps
            }

            "Forearms" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutForearms
            }

            "Abdominals" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutAbdominals
            }

            "Middle back" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutMiddleBack
            }

            "Lower back" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutLowerBack
            }

            "Glutes" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutGlutes
            }

            "Abductors" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutAbductors
            }

            "Quadriceps" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutQuadriceps
            }

            "Adductors" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutAdductors
            }

            "Hamstrings" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutHamstrings
            }

            "Calves" -> {
                groupMuscleSave = group
                itemGroup.value = muscleGroup.workoutCalves
            }
        }
        listSelected.value = itemGroup.value
        filterGroupList(searchWorkout.value)
        return itemGroup.value
    }


}