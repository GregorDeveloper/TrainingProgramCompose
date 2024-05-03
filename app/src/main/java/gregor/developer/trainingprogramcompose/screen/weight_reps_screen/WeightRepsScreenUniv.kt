package gregor.developer.trainingprogramcompose.screen.weight_reps_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.title_date.TitleDate
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen.NewWeightRepsScreen
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen.UiNewWeightRepsScreen
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NoteScreen.NoteScreen


@Preview(showBackground = true)
@Composable
fun WeightRepsScreenUniv() {
    val weightRepsTest = listOf<WeightRepsWorkoutItem>(
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
        TitleDate(
            title = "title",
            modifier = Modifier.weight(0.2f)
        )

        LazyColumn(
            modifier = Modifier
                .padding(5.dp)
                .weight(0.8f)
        ) {
            itemsIndexed(
                weightRepsTest
            ) { index, item ->
                UiNewWeightRepsScreen(weightReps = item, number = index) {

                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(
                        bottom = 55.dp
                    ),
                backgroundColor = Color.DarkGray,
                shape = RoundedCornerShape(9.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Note",
                            modifier = Modifier
                                .padding(3.dp)
                                .weight(1f),
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                        Button(onClick = { /*TODO */ },
                            modifier = Modifier.padding(end = 5.dp)) {
                            Text(text = stringResource(id = R.string.add))
                        }
                    }
                    NoteScreen()

                }
            }

        }
    }
}