package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightReps
import gregor.developer.trainingprogramcompose.screen.title_date.TitleDate
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen.UiNewWeightRepsScreen
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NoteScreen.NoteScreen


@Composable
fun WeightRepsScreenUniv(
    viewModel: WeightRepsUnivViewModel = hiltViewModel()
) {
    val weightRepsTest = mutableListOf<WeightRepsWorkoutItem>()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .padding(3.dp),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(9.dp),
        ) {
            TitleDate(
                viewModel = viewModel,
                openChangeDate = viewModel.openChangeDate,
                date = viewModel.date.value){event ->
                viewModel.onLastOrNextEvent(event)
            }
        }

        Card(
            modifier = Modifier
                .padding(3.dp)
                .weight(1f),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(9.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(
                        id = R.string.machine_shoulder_military_press
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
                TitleTableWeightReps()

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = if (weightRepsTest.isEmpty()) Alignment.Center else Alignment.TopCenter
                ) {
//                    if (weightRepsTest.isEmpty()) {
//                        Text(
//                            text = "Empty table",
//                            style = TextStyle(
//                                fontSize = 25.sp,
//                                color = Color.White
//                            )
//                        )
//                    } else {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(modifier = Modifier.weight(0.8f)) {
                            itemsIndexed(
                                viewModel.items
                            ) { index, item ->
                                UiNewWeightRepsScreen(weightReps = item, number = index) {

                                }
                            }
                        }
                    }
                    //  }
                }

            }
        }
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(
                    // bottom = 55.dp
                ),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(9.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp),
            ) {
                NoteScreen(modifier = Modifier.weight(1f), viewModel)
                if (viewModel.openChangeDate) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        IconButton(onClick = {
                            viewModel.onEvent(WeightRepsUnivEvent.OpenDialog)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.add_icon),
                                contentDescription = "delete note",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = {
                            viewModel.onEvent(
                                WeightRepsUnivEvent.SaveNote(
                                    viewModel.note.value
                                )
                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.save_icon),
                                contentDescription = "save",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_icon),
                                contentDescription = "save",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.info_icon),
                                contentDescription = "delete note",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

    }

    DialogWeightReps(dialogController = viewModel)
}
