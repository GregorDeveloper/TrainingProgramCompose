package gregor.developer.trainingprogramcompose.screen.main_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.MainDialog
import gregor.developer.trainingprogramcompose.navigation.NavigationGraph
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarEvent
import gregor.developer.trainingprogramcompose.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    trainingUpdate: Boolean,
    mainNavController: NavHostController,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: MainScreenViewModel = hiltViewModel(),
    ) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when(event){
                Lifecycle.Event.ON_START -> {
                    Log.d("LogLifecycle", "ON_START")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("LogLifecycle", "ON_STOP")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("LogLifecycle", "ON_PAUSE")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("LogMainScreen", trainingUpdate.toString())

                }
                else ->{

                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.d("LogMain", navBackStackEntry?.destination?.route.toString())
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (currentRoute == Routes.CALENDAR_SCREEN ||
                currentRoute == Routes.TRAINING_LIST ||
                currentRoute == Routes.PROGRESS ||
                currentRoute == Routes.SETTINGS
            ) {
                BottomNav(navController)
            }

        },
        floatingActionButton = {
            if (currentRoute == Routes.TRAINING_LIST) {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(
                            MainScreenEvent.OnNewItemClick(
                                currentRoute
                            )
                        )
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_icon),
                        contentDescription = "Add",
                        tint = Color.Gray
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {
        NavigationGraph(trainingUpdate, navController) { route ->
            mainNavController.navigate(route)
        }
        MainDialog(viewModel)
    }
    BackHandler {
        navController.popBackStack()
    }
}



