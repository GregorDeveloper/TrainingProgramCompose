package gregor.developer.trainingprogramcompose.screen.calendar_screen.calendar_bottom

import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.utils.Routes

sealed class CalendarBottomNav(
    val title: Int,
    val route: String
) {
    object Training: CalendarBottomNav(R.string.training, Routes.LIST_WORKOUT)
    object CaloriesCalculation: CalendarBottomNav(R.string.calculation_calorie, Routes.FOOD_SCREEN)
}