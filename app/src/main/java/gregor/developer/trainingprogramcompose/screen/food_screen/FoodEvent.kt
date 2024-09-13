package gregor.developer.trainingprogramcompose.screen.food_screen

import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate

sealed class FoodEvent {
    data class OpenDialog(val foodCalories: CalculationCalorieItem): FoodEvent()

    data class SaveAndBack(val foodDate: FoodDate): FoodEvent()

    object SaveListAndBack: FoodEvent()

    object ClearList: FoodEvent()

    data class AddListFood(val foodDate: FoodDate): FoodEvent()


}