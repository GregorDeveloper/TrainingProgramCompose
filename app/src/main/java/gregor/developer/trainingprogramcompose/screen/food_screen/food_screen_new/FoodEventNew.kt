package gregor.developer.trainingprogramcompose.screen.food_screen.food_screen_new
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutDate

sealed class FoodEventNew {
    data class OpenDialog(val foodCalories: CalculationCalorieItem): FoodEventNew()

    data class SaveAndBack(val foodDate: WorkoutDate): FoodEventNew()

    object SaveListAndBack: FoodEventNew()

    object ClearList: FoodEventNew()

    data class AddListFood(val foodDate: WorkoutDate): FoodEventNew()
}