package gregor.developer.trainingprogramcompose.screen.workout_screen.user_workout


import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.utils.Routes
import gregor.developer.trainingprogramcompose.utils.UiEvent


@Composable
fun UserWorkoutScreen(
    viewModel: UserWorkoutScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val toastText = context.getString(R.string.no_data_toast)
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }
    }
    val trainingList = viewModel.itemsList!!.collectAsState(emptyList())

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
            itemsIndexed(trainingList.value, key = {
                _, listItem ->
                listItem.hashCode()
            }) { index, item ->
                UiUserWorkOutScreen(item) { event ->
                    onNavigate(
                        event
                    )
                }
            }

        }
        IconButton(
            onClick = {
                Log.d("LogUserWorkOut", " x")
                if(viewModel.date != " "){
                    viewModel.onEvent(UserWorkoutEvent.OnSaveList(Routes.SAVE_LIST_AND_BACK))
                }else{ //Добавить проверку
                    onNavigate(
                        Routes.WORKOUT_LIST +  "/${" "}" + "/${viewModel.listId}"
                    )
                }

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