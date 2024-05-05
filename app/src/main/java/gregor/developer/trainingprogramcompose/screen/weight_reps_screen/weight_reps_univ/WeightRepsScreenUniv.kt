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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.title_date.TitleDate
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen.UiNewWeightRepsScreen
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NoteScreen.NoteScreen


@Preview(showBackground = true)
@Composable
fun WeightRepsScreenUniv() {
    val weightRepsTest = listOf<WeightRepsWorkoutItem>(
        WeightRepsWorkoutItem(
            0,
            "Test1",
            "50",
            "5",
            "24"
        ),
        WeightRepsWorkoutItem(
            0,
            "Test1",
            "50",
            "5",
            "24"
        ),
        WeightRepsWorkoutItem(
            0,
            "Test1",
            "50",
            "5",
            "24"
        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),
//        WeightRepsWorkoutItem(
//            0,
//            "Test1",
//            "50",
//            "5",
//            "24"
//        ),

    )



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
                title = "title",
                modifier = Modifier.weight(0.2f)
            )
        }

        Card(
            modifier = Modifier
                .padding(3.dp)
                .weight(0.8f),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(9.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {
                TitleTableWeightReps()

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = if (weightRepsTest.isEmpty()) Alignment.Center else Alignment.TopCenter
                ) {
                    if (weightRepsTest.isEmpty()) {
                        Text(
                            text = "Empty table",
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White
                            )
                        )
                    } else {
                        LazyColumn() {
                            itemsIndexed(
                                weightRepsTest
                            ) { index, item ->
                                UiNewWeightRepsScreen(weightReps = item, number = index) {

                                }
                            }
                        }
                    }
                }

            }
        }
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(3.dp),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(9.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.machine_shoulder_military_press),
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f),
                    fontSize = 18.sp,
                    color = Color.White,
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.save_icon),
                        contentDescription = "save",
                        tint = Color.White
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(
                        // bottom = 55.dp
                    ),
                backgroundColor = Color.DarkGray,
                shape = RoundedCornerShape(9.dp),
            ) {
                NoteScreen()

            }

        }
    }
}