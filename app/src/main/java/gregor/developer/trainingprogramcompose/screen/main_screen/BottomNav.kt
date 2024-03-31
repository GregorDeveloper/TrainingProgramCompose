package gregor.developer.trainingprogramcompose.screen.main_screen

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import gregor.developer.trainingprogramcompose.utils.Routes


@Composable
fun BottomNav(
    navController: NavHostController
) {
    val listItems = listOf(
        BottomNavItem.TrainingItem,
        BottomNavItem.MyProgress,
        BottomNavItem.Workout,
        BottomNavItem.Settings
    )
    val str = " "
    BottomNavigation(backgroundColor = Color.DarkGray) {
        listItems.forEach { bottomNavItem ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            BottomNavigationItem(
                selected = currentRoute == bottomNavItem.route,
                onClick = {
                    if (bottomNavItem.route == Routes.TRAINING_LIST){
                        Log.d("LogBottomNav", "TRAINING_LIST")
                        navController.navigate(bottomNavItem.route + "/${str}")
                    }else{
                        navController.navigate(bottomNavItem.route)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavItem.iconId),
                        contentDescription = "icon"
                    )
                },
                label = {
                    Text(text = stringResource(bottomNavItem.title))
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.LightGray
            )
        }
    }
}