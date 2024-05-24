package gregor.developer.trainingprogramcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen.NewWeightRepsScreen
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.OldWeightRepsScreen.OldWeightRepsScreen
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun WeightRepsNavigationGraph(
    navController: NavHostController,
    workoutName: String
) {

    NavHost(navController = navController, startDestination = Routes.NEW_WEIGHT_REPS
    ){
        composable(Routes.NEW_WEIGHT_REPS
              //  + "/{workoutName}",
//            arguments = listOf(
//                navArgument("workoutName") {
//                    type = NavType.StringType
//                    defaultValue = ""
//                }
//            )
        ){
            NewWeightRepsScreen(workoutName)
        }

        composable(Routes.NEXT_WEIGHT_REPS){
            OldWeightRepsScreen()
        }

//        composable(Routes.NOTE){
//            NoteScreen(modifier = Modifier)
//        }
    }
}