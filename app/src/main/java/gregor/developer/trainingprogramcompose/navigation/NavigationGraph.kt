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
    navController: NavHostController,
    onNavigate: (String) -> Unit
) {

    NavHost(navController = navController, startDestination = Routes.CALENDAR_SCREEN) {
        composable(Routes.CALENDAR_SCREEN) { entry ->
            val trainingUpdate = entry.savedStateHandle.get<Boolean>("add_training")
            CalendarScreen(trainingUpdate ?: false)
            { route ->
                Log.d("NavigationGraph", route)
                if(route.substring(0, route.indexOf("/")) == Routes.WEIGHT_REPS){
                    onNavigate(route)
                }else{
                    navController.navigate(route)
                }
            }
        }
        composable(Routes.PROGRESS) {
            ProgressScreen()
        }
        composable(
            Routes.WORKOUT_LIST
                    + "/{date}" + "/{listId}",
            arguments = listOf(
                navArgument("date") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("listId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            WorkoutScreen() {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("add_training", it)
                navController.popBackStack()
            }
        }
        composable(Routes.TRAINING_LIST) {
            TrainingListScreen(false)
            { route ->
                  navController.navigate(route)
            }
        }
        composable(Routes.TRAINING_LIST + "/{date}",
            arguments = listOf(
                navArgument("date") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {entry ->
            val trainingUpdate = entry.savedStateHandle.get<Boolean>("add_training")
            TrainingListScreen(trainingUpdate ?: false)
            { route ->
                if(trainingUpdate != null){
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("add_training", true)
                    navController.popBackStack()
                }else{
                    navController.navigate(route)
                }

                //onNavigate(route)
            }
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }

        composable(Routes.USER_WORKOUT_LIST
                + "/{listId}"
                + "/{date}"
            ,
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("date"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            UserWorkoutScreen()
            { route ->
               // navController.popBackStack()
                if(route == "Back"){
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("add_training", true)
                    navController.popBackStack()
                }else{
                    navController.navigate(route)
                }

            }
        }
    }
}