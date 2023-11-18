package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem

@Preview(showBackground = true)
@Composable
fun NewWeightRepsScreen() {

    val testList = listOf<WeightRepsWorkoutItem>(
        WeightRepsWorkoutItem(workOutName = "Test", weight = "80", reps = "10", date = "20.03.23"),
        WeightRepsWorkoutItem(workOutName = "Test", weight = "90", reps = "8", date = "20.03.23"),
        WeightRepsWorkoutItem(workOutName = "Test", weight = "95", reps = "6", date = "20.03.23"),
        WeightRepsWorkoutItem(workOutName = "Test", weight = "100", reps = "3", date = "20.03.23"),
        WeightRepsWorkoutItem(workOutName = "Test", weight = "100", reps = "3", date = "20.03.23"),
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 2.dp,
                end = 2.dp
            ),
//        shape = CircleShape,
//        elevation = 5.dp,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Number",
                    modifier = Modifier.weight(1f)
                        .border(1.dp, Color.Green),
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center

                )
                Text(
                    text = "Weight",
                    modifier = Modifier.weight(2f)
                        .border(1.dp, Color.Green),
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Reps",
                    modifier = Modifier.weight(2f)
                        .border(1.dp, Color.Green),
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    testList
                ) { index, item ->
                    UiNewWeightRepsScreen(item, index)
                }
//            itemsIndexed(testList, key = {
//                    _, listItem ->
//                listItem.hashCode()){ index, item ->
//                UiNewWeightRepsScreen()
//            }
//        }
            }

        }
    }
}