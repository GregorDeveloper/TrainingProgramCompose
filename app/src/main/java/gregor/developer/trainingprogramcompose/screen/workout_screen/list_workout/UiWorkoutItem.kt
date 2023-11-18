package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutItem


@Composable
fun UiWorkoutItem(
    item: WorkoutItem,
    onEvent: (WorkoutEvent) -> Unit
) {

    Card(

        modifier = Modifier
            .padding(2.dp)
            .clickable {
                onEvent(WorkoutEvent.OnShowDescriptionDialog(item))
            },
        backgroundColor = Color.DarkGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painterResource(
                        item.imageId[0]
                    ),
                    contentDescription = "Workout"
                )
                Text(
                    text = stringResource(item.name),
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = muscle(item),

                style = TextStyle(
                    color = Color.Green,
                    fontSize = 14.sp,
                )
            )
        }
    }

}

@Composable
fun muscle(item: WorkoutItem): String {

    var muscle = ""
    val lastElement = item.muscleGroup.lastIndex
    item.muscleGroup.forEachIndexed { index, i ->
        if (lastElement == index) {
            muscle += stringResource(i)
        } else {
            muscle += stringResource(i) + ", "
        }
    }
    return muscle
}

