package gregor.developer.trainingprogramcompose.screen.food_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem

import gregor.developer.training_program_compose.data.repository.CalculationCalorieRepository

import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodScreenViewModel @Inject constructor(
    private val repository: CalculationCalorieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val checkingFood = mutableListOf<FoodDate>()
    val searchFood = mutableStateOf("")
    val fabVisible = mutableStateOf(false)
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    var date: String? = null
    var listId: Int? = null
    init {
        date = savedStateHandle.get<String>("date")
        listId = savedStateHandle.get<Int>("listId")
        Log.d("LogId", listId.toString())
        Log.d("LogId", "asd")
    }

    fun onEvent(event: FoodEvent) {
        when (event) {
            is FoodEvent.SaveListAndBack -> {
                viewModelScope.launch {
                    if(checkingFood.isNotEmpty()){
                        for (food in checkingFood){
                            repository.insertItem(reworkItem(food, listId = listId ?: -1))
                        }
                        sendUiEvent(UiEvent.BackStack)
                    }
                }
            }

            is FoodEvent.SaveAndBack -> {
                viewModelScope.launch {
                    repository.insertItem(reworkItem(event.foodDate, listId = listId ?: -1))
                    sendUiEvent(UiEvent.BackStack)
                }


            }

            is FoodEvent.OpenDialog -> {

            }

            is FoodEvent.ClearList -> {
                checkingFood.clear()
                fabVisible.value = false
                Log.d("LogClearList", checkingFood.size.toString())
            }

            is FoodEvent.AddListFood -> {
                addListFood(event.foodDate)
                fabVisible.value = checkingFood.size > 0
            }
        }
    }

    fun addListFood(foodDate: FoodDate) {
        if (foodDate.checking) {
            checkingFood.add(foodDate)
        } else {
            checkingFood.remove(foodDate.copy(checking = true))
        }
    }

    private suspend fun sendUiEvent(event: UiEvent) {
            _uiEvent.send(event)
    }

    private fun reworkItem(food: FoodDate, listId: Int): CalculationCalorieItem{
        return CalculationCalorieItem(
            id = if(listId == -1) null else listId,
            name = food.name,
            calories = food.calories,
            proteins = food.proteins,
            fats = food.fats,
            carbohydrates = food.carbohydrates,
            date = date!!
        )
    }
}