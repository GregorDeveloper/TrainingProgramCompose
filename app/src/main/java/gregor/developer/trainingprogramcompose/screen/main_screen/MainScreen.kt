package gregor.developer.trainingprogramcompose.screen.main_screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.MainDialog
import gregor.developer.trainingprogramcompose.navigation.NavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainNavController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel(),

) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            //.background(Color.Green)
            .fillMaxSize(),
        bottomBar = {
            BottomNav(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(MainScreenEvent.OnShowEditDialog)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_icon),
                    contentDescription = "Add",
                    tint = Color.Gray
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {


        NavigationGraph(navController){route ->
            mainNavController.navigate(route)
        }
        MainDialog(viewModel)
    }
    BackHandler {
        navController.popBackStack()
    }
}

