package gregor.developer.trainingprogramcompose.screen.calendar_screen.calendar_bottom

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import gregor.developer.trainingprogramcompose.screen.calendar_screen.data.CanvasParametr


@Composable
fun CalendarBottomNavItem(
    navController: NavHostController,
    saveCurrentRoute: (String) -> Unit
) {
    val listItem = listOf(
        CalendarBottomNav.Training,
        CalendarBottomNav.CaloriesCalculation
    )

    BottomNavigation(modifier = Modifier.fillMaxWidth(), backgroundColor = Color.Transparent) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        saveCurrentRoute(currentRoute.toString())
        listItem.forEach { calendarBottomNav ->
            BottomNavigationItem(
                selected = currentRoute == calendarBottomNav.route,
                onClick = {
                  navController.navigate(calendarBottomNav.route)
                },
                icon = {},
                label = {
                    Text(text = stringResource(calendarBottomNav.title),
                        style = TextStyle(
                            fontSize = 16.sp
                        ))
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.White,
            )
        }
    }
}