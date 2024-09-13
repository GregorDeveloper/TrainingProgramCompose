package gregor.developer.trainingprogramcompose.screen.FoodList

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import gregor.developer.training_program_compose.data.repository.CalculationCalorieRepository
import gregor.developer.trainingprogramcompose.screen.swipe_screen.SwipeToDismissController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val repository: CalculationCalorieRepository
): ViewModel(), SwipeToDismissController {
    var listFoodFlow: Flow<List<CalculationCalorieItem>>? = null

    val sumCal = mutableStateOf(0.0)
    val sumProt = mutableStateOf(0.0)
    val sumFat = mutableStateOf(0.0)
    val sumCarb = mutableStateOf(0.0)
    val selectedDate = mutableStateOf("")
    override var cancelSwipe = mutableStateOf(false)
    init {
        listFoodFlow = repository.getAllItemByDate(date = "0$selectedDate")
    }

    fun onEvent(event: FoodListEvent){
        when(event){
            is FoodListEvent.ClickFood -> {

            }
            is FoodListEvent.DeleteFood -> {

            }
            is FoodListEvent.EditFood -> {

            }
        }
    }

    fun getListFoodSelectedDate(date: String){
        Log.d("LogFoodData", "$date - date")
        val dt = "0$date"
        listFoodFlow = repository.getAllItemByDate(dt)
        Log.d("LogFoodData", listFoodFlow.toString())

        viewModelScope.launch {
            listFoodFlow?.collect{ food ->
                for(i in food.withIndex()){
                    Log.d("LogFoodData", i.value.name)
                }
            }
        }


    }
}