package gregor.developer.trainingprogramcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gregor.developer.trainingprogramcompose.screen.progress_screen.ProgressScreen
import gregor.developer.trainingprogramcompose.screen.settings_screen.SettingsScreen
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.WorkoutScreen
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun NavigationGraph(navController: NavHostController,
                    onNavigate: (String) -> Unit
                    ) {

    NavHost(navController = navController, startDestination = Routes.TRAINING_LIST){

        composable(Routes.TRAINING_LIST){
            TrainingListScreen(){route ->
                onNavigate(route)
            }
        }
        composable(Routes.PROGRESS){
            ProgressScreen()
        }
        composable(Routes.WORKOUT_LIST){
            WorkoutScreen()
        }
        composable(Routes.SETTINGS){
            SettingsScreen()
        }
    }
}