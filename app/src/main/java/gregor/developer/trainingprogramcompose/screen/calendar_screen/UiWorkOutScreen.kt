package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.utils.Routes


@Composable
fun UiWorkOutScreen(
    item: WorkoutListItem,
    onEvent: (String) -> Unit
) {
    Log.d("MyLogScreen", item.workoutName)
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onEvent(
                    Routes.WEIGHT_REPS_SCREEN_UNIV
                    //Routes.WEIGHT_REPS + "/${item.workoutName}"
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