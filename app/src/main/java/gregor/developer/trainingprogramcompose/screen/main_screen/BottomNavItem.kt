package gregor.developer.trainingprogramcompose.screen.main_screen

import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.utils.Routes


sealed class BottomNavItem(
    val title: Int,
    val iconId: Int,
    val route: String
){

    object TrainingItem: BottomNavItem(R.string.training_list, R.drawable.training_list, Routes.TRAINING_LIST)
    object MyProgress: BottomNavItem(R.string.my_progress , R.drawable.progress_icon, Routes.PROGRESS)
    object Workout: BottomNavItem(R.string.workout, R.drawable.workout_icon, Routes.WORKOUT_LIST)
    object Settings: BottomNavItem(R.string.settings, R.drawable.settings_icon, Routes.SETTINGS)
}
