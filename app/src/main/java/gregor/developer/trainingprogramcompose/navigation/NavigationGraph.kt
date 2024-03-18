package gregor.developer.trainingprogramcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarScreen
import gregor.developer.trainingprogramcompose.screen.progress_screen.ProgressScreen
import gregor.developer.trainingprogramcompose.screen.settings_screen.SettingsScreen
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout.WorkoutScreen
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun NavigationGraph(
    navController: NavHostController,
    onNavigate: (String) -> Unit
) {

    NavHost(navController = navController, startDestination = Routes.CALENDAR_SCREEN) {
        composable(Routes.CALENDAR_SCREEN) {
            CalendarScreen()
            { route ->
                onNavigate(route)
            }
        }
        composable(Routes.PROGRESS) {
            ProgressScreen()
        }
        composable(Routes.WORKOUT_LIST) {
            WorkoutScreen(navController = navController)
        }
        composable(Routes.TRAINING_LIST) {
            TrainingListScreen()
            { route ->
                onNavigate(route)
            }
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}