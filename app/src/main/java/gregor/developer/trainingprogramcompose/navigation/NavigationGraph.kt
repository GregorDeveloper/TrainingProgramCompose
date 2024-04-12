package gregor.developer.trainingprogramcompose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarScreen
import gregor.developer.trainingprogramcompose.screen.progress_screen.ProgressScreen
import gregor.developer.trainingprogramcompose.screen.settings_screen.SettingsScreen
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout.WorkoutScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.user_workout.UserWorkoutScreen
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun NavigationGraph(
    trainingUpdate: Boolean,
    navController: NavHostController,
    onNavigate: (String) -> Unit
) {

    NavHost(navController = navController, startDestination = Routes.CALENDAR_SCREEN) {
        composable(Routes.CALENDAR_SCREEN) { entry ->
            CalendarScreen(trainingUpdate)
            { route ->
                    onNavigate(route)
            }
        }
        composable(Routes.PROGRESS) {
            ProgressScreen()
        }
        composable(Routes.TRAINING_LIST) {
            TrainingListScreen(false)
            { route ->
                  onNavigate(route)
            }
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}