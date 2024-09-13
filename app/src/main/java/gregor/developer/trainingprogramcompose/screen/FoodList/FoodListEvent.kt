package gregor.developer.trainingprogramcompose.screen.FoodList

import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem

sealed class FoodListEvent {
    data class ClickFood(val item: CalculationCalorieItem): FoodListEvent()

    data class DeleteFood(val item: CalculationCalorieItem): FoodListEvent()

    data class EditFood(val item: CalculationCalorieItem): FoodListEvent()

}