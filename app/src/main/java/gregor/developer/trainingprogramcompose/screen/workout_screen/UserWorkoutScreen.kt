package gregor.developer.trainingprogramcompose.screen.workout_screen



import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.utils.Routes



@Composable
fun UserWorkoutScreen(
    viewModel: WorkScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (lazyColumn, iconButton) = createRefs()

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .constrainAs(lazyColumn) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            item {
                Text(text = "First item")
            }

            items(5) { index ->
                Text(text = "Item: $index")
            }
            item {
                Text(text = "Last item")
            }

        }
        IconButton(
            onClick = {
                onNavigate(Routes.WORKOUT_LIST + "/${viewModel.listId}" + "/${viewModel.itemId}"
                )
            },
            modifier = Modifier.constrainAs(iconButton) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }.padding(15.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.add_icon_48),
                contentDescription = "Add workout",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.DarkGray)
            )
        }
    }
}