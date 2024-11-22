package gregor.developer.trainingprogramcompose.screen.food_screen.food_screen_new

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.CalculationCalorieRepository
import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutDate
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DialogListController
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DialogListEvent
import gregor.developer.trainingprogramcompose.screen.food_screen.FoodEvent
import gregor.developer.trainingprogramcompose.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodScreenNewViewModel @Inject constructor(
    private val repository: CalculationCalorieRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel(), DialogListController {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var foodItem: WorkoutListItem? = null
    var listId: Int? = null
    override var list: MutableList<WorkoutDate> = mutableListOf()
        private set
    override var openDialog: MutableState<Boolean> = mutableStateOf(false)
        private set
    override var dateDialog: MutableState<String> = mutableStateOf("")
        private set

    var date: String? = null
    override var workoutOrFood: MutableState<Boolean> = mutableStateOf(false)

    val fabVisibility = mutableStateOf(false)
    val search = mutableStateOf("")
    init {
        date = savedStateHandle.get<String>("date")
        listId = savedStateHandle.get<Int>("listId")
        dateDialog.value = "${date}: "
        Log.d("LogFood", listId.toString())
    }

    fun onEvent(event: FoodEventNew){
        when(event){
            is FoodEventNew.SaveAndBack -> {
                list.add(
                    event.foodDate
                )
                openDialog.value = true
            }
            is FoodEventNew.SaveListAndBack -> {
                if (list.size > 0) {
                    openDialog.value = true
                }
            }
            is FoodEventNew.AddListFood -> {
                if(event.foodDate.checking){
                    list.add(
                        event.foodDate
                    )
                }else {
                    for ((index, value) in list.withIndex()) {
                        if (value.name.equals(event.foodDate.name)) {
                            list.removeAt(index)
                            break
                        }
                    }
                }
                checkFabPosition()
            }
            is FoodEventNew.ClearList -> {
                if (list.size > 0) list.clear()
                Log.d("LogList", list.size.toString())
                checkFabPosition()
            }
            else -> {

            }
        }
    }


    override fun onDialogEvent(event: DialogListEvent) {
        when(event){
            is DialogListEvent.OnConfirm -> {
                    viewModelScope.launch {
                        list.forEach { item ->
                            repository.insertItem(
                                CalculationCalorieItem(
                                    id = listId,
                                    name = item.name,
                                    calories = item.equipment.toDouble(),
                                    proteins = item.primaryMuscles.toDouble(),
                                    fats = item.secondaryMuscles.toDouble(),
                                    carbohydrates = item.additionalPar.toDouble(),
                                    date = date!!,
                                )
                            )
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


    private fun checkFabPosition() {
        fabVisibility.value = if (list.size > 0) true else false
    }
    private suspend fun sendUiEvent(event: UiEvent) {
        _uiEvent.send(event)
    }
}