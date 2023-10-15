package gregor.developer.trainingprogramcompose.screen.workout_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.utils.Routes


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UiUserWorkOutScreen(
    item: WorkoutListItem,
    onEvent: (WorkoutEvent) -> Unit
) {
    Card(
        onClick = {

        },
        modifier = Modifier.fillMaxWidth()
            .padding(2.dp)
            .clickable {
                       onEvent(
                           WorkoutEvent.OnItemClick(
                               Routes.WEIGHT_REPS + "/${item.id}"
                           )
                       )
            },
        backgroundColor = Color.DarkGray
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = item.workoutName,
                style = TextStyle(
                    color = Color.Green,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

}