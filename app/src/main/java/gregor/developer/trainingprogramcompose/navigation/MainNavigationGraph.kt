package gregor.developer.trainingprogramcompose.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import gregor.developer.training_program_compose.weight_reps_screen.WeightRepsScreen
import gregor.developer.trainingprogramcompose.screen.main_screen.MainScreen
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.user_workout.UserWorkoutScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout.WorkoutScreen
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun MainNavigationGraph(
    // onNavigate: (String) -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.USER_WORKOUT_LIST
                + "/{listId}"
                + "/{date}",
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("date") {
                    type = NavType.StringType
                    defaultValue = " "
                }
            )
        ) {
            UserWorkoutScreen()
            { route ->
                Log.d("LogNavigation", route)
                if(route == Routes.SAVE_LIST_AND_BACK){
                    Log.d("LogNavigation", "if Ok")
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(Routes.ADD_TRAINING, true)
                    navController.popBackStack()
                    Log.d("LogNavigation", "if Ok")
                }else{
                    Log.d("LogNavigation", "else Ok")
                    navController.navigate(route)
                }
            }
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
        composable(Routes.TRAINING_LIST + "/{date}",
            arguments = listOf(
                navArgument("date") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { entry ->
            val trainingUpdate = entry.savedStateHandle.get<Boolean>(Routes.ADD_TRAINING) ?: false
            TrainingListScreen(trainingUpdate)
            { route ->
                Log.d("LogNavigation", trainingUpdate.toString())
                if (trainingUpdate){
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("add_training", trainingUpdate)
                    navController.popBackStack()
                }else{
                    navController.navigate(route)
                }
            }
        }

        composable(Routes.WEIGHT_REPS + "/{workoutName}",
            arguments = listOf(
                navArgument("workoutName") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )) {
            WeightRepsScreen() { route ->
                navController.navigate(route)
            }
        }
        composable(Routes.MAIN_SCREEN) { entry ->
            val trainingUpdate = entry.savedStateHandle.get<Boolean>("add_training")
            Log.d("LogNavigation", trainingUpdate.toString())
            MainScreen(trainingUpdate ?: false, navController)
        }
    }
}