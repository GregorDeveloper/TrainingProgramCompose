package gregor.developer.trainingprogramcompose.screen.calendar_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ.LastOrNextTrainingEvent
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun UiDateList(
    item: WeightRepsWorkoutItem,
    clickOldDate: (LastOrNextTrainingEvent) -> Unit
) {
    Box(modifier = Modifier
        //.fillMaxWidth()
        .clickable {
                clickOldDate(LastOrNextTrainingEvent.ClickDate(item.date))
        },
        contentAlignment = Alignment.Center
       ) {
        Text(text = item.date,
            style = TextStyle(
                color = Color.White
            ),
            modifier = Modifier.padding(3.dp)
        )
    }

}