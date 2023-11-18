

package gregor.developer.training_program_compose.weight_reps_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.navigation.WeightRepsNavigationGraph
import gregor.developer.trainingprogramcompose.screen.main_screen.BottomNav
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.Bottom.WeightRepsBottomNav
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.WeightRepsViewModel
import gregor.developer.trainingprogramcompose.screen.workout_screen.user_workout.UserWorkoutScreenViewModel
import gregor.developer.trainingprogramcompose.ui.theme.Grey
import gregor.developer.trainingprogramcompose.utils.Routes


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeightRepsScreen(
   // mainNavController: NavHostController,
    viewModel: WeightRepsViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            //.background(Color.Green)
            .fillMaxSize(),
        bottomBar = {
            WeightRepsBottomNav(navController, false)
        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    //   viewModel.onEvent(MainScreenEvent.OnShowEditDialog)
//                }
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.add_icon),
//                    contentDescription = "Add",
//                    tint = Color.Gray
//                )
//            }
//        },
//        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true,
    ) {


        WeightRepsNavigationGraph(navController)
        //   mainNavController.navigate(route)

//       // MainDialog(viewModel)
        BackHandler() {
            onNavigate(
                Routes.USER_WORKOUT_LIST + "/${viewModel.listId}"
            )

        }
    }

}

