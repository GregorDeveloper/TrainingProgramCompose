package gregor.developer.trainingprogramcompose.screen.workout_screen


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R

import gregor.developer.trainingprogramcompose.data.static_data.MuscleList
import gregor.developer.trainingprogramcompose.dialog.dialog_description.DialogDescription


val muscleGroup = MuscleList()


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun WorkoutScreen(
    viewModel: WorkScreenViewModel = hiltViewModel(),
) {
    val itemGroup = viewModel.itemGroup.value
    //28

    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = Modifier.fillMaxSize()) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.searchWorkout.value,
                        onValueChange = { workoutString ->
                            viewModel.onEvent(WorkoutEvent.OnSearchWorkout(workoutString))
                        },
                        label = {
                            Text(
                                text = "Search workout",
                                fontSize = 12.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Gray,
                            focusedIndicatorColor = Color.Green,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Green,
                        ),
                        singleLine = true
                    )
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(R.drawable.search_icon),
                            contentDescription = "Search"
                        )
                    }
                }
            }

            Column {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                ) {
                    itemsIndexed(muscleGroup.muscleGroupList) { index, item ->
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
                    itemsIndexed(itemGroup) { index, item ->
                        UiWorkoutItem(item) { event ->
                            viewModel.onEvent(event)
                        }
                    }
                }
            }
        }

        DialogDescription(viewModel)
    }
}


