package gregor.developer.trainingprogramcompose.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import gregor.developer.training_program_compose.weight_reps_screen.WeightRepsScreen
import gregor.developer.trainingprogramcompose.screen.main_screen.MainScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.UserWorkoutScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.WorkoutScreen
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun MainNavigationGraph(
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.USER_WORKOUT_LIST
                + "/{listId}",
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {

            UserWorkoutScreen() { route ->
                navController.navigate(route)
            }
        }
        composable(
            Routes.WORKOUT_LIST + "/{listId}" + "/{itemId}",
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("itemId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            WorkoutScreen()
        }
        composable(Routes.WEIGHT_REPS + "/{listId}",
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )) {
            WeightRepsScreen()
        }
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navController)
        }
    }
}