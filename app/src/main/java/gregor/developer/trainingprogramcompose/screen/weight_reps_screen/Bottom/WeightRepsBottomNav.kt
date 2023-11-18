package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.Bottom

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
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.main_screen.BottomNavItem

@Composable
fun WeightRepsBottomNav(
    navController: NavHostController,
    addWeightReps: Boolean
) {
    val listItems = listOf(
        WeightRepsBottomNavItem.NewWeightReps,
        WeightRepsBottomNavItem.OldWeightReps,
        WeightRepsBottomNavItem.NoteWeightReps,
    )

    BottomNavigation(backgroundColor = Color.DarkGray) {
        listItems.forEach { weightRepsBottomNavItem ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            BottomNavigationItem(
                selected = currentRoute == weightRepsBottomNavItem.route,
                onClick = {
                    navController.navigate(weightRepsBottomNavItem.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = weightRepsBottomNavItem.iconId),
                        contentDescription = "icon"
                    )
                },
                label = {
                    Text(text = stringResource(weightRepsBottomNavItem.title))
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray
            )
        }
    }
}