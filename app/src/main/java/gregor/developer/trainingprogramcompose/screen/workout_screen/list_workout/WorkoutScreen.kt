package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout


import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import gregor.developer.trainingprogramcompose.data.static_data.MuscleList
import gregor.developer.trainingprogramcompose.dialog.dialog_description.DialogDescription
import gregor.developer.trainingprogramcompose.utils.Routes


val muscleGroup = MuscleList()


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkoutScreen(
   // navController: NavHostController,
    viewModel: WorkScreenViewModel = hiltViewModel(),
    onNavigate: (Boolean) -> Unit
) {
    val itemGroup = viewModel.itemGroup.value

    val scaffoldState = rememberScaffoldState()
    TopAppBar() {

    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchScreen(viewModel.searchWorkout) {
                viewModel.onEvent(WorkoutEvent.OnSearchWorkout(viewModel.searchWorkout.value))
            }
        }

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                ) {
                    itemsIndexed(viewModel.groupList.value) { index, item ->
                        MuscleItem(item) { muscleItem ->
                            viewModel.getListWorkOut(muscleItem)
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    itemsIndexed(
                        viewModel.itemGroup.value
                    ) { index, item ->
                        UiWorkoutItem(item) { event ->
                            viewModel.onEvent(event)
                        }
                    }
                }
            }
        }

        DialogDescription(viewModel)
       {
            Log.d("MyLogCalendarScreen", it.toString() +" WorkoutScreen")
            onNavigate(it)
        }
    }
    BackHandler {
        onNavigate(false)
    }

}


