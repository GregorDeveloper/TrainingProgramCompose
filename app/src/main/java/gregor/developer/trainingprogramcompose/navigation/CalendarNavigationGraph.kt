package gregor.developer.trainingprogramcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import gregor.developer.trainingprogramcompose.screen.FoodList.FoodList
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarScreenViewModel
import gregor.developer.trainingprogramcompose.screen.calendar_screen.ListWorkout.ListTrainingOrCalculation
import gregor.developer.trainingprogramcompose.screen.calendar_screen.data.CanvasParametr
import gregor.developer.trainingprogramcompose.screen.progress_screen.CalculationCalorie
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun CalendarNavigationGraph(navController: NavHostController, viewModel: CalendarScreenViewModel){
    NavHost(navController = navController, startDestination = Routes.LIST_WORKOUT){
       composable(Routes.LIST_WORKOUT){
           ListTrainingOrCalculation(viewModel = viewModel)
       }
        composable(Routes.FOOD_SCREEN){
            FoodList(viewModel = viewModel)
        }
    }
}