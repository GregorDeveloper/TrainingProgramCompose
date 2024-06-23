package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListEvent
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ.WeightRepsUnivEvent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UiNewWeightRepsScreen(
    weightReps: WeightRepsWorkoutItem,
    number: Int,
    onEvent: (WeightRepsUnivEvent) -> Unit
) {
    val editTitle = stringResource(R.string.edit_weight_reps_dialog)
    val deleteTitle = stringResource(R.string.delete_weight_reps_number)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    onEvent(WeightRepsUnivEvent.OnShowEditDialog(number, editTitle))
                },
                onLongClick = {
                    onEvent(WeightRepsUnivEvent.OpenDeleteDialog(number, deleteTitle))
                }
            ),
    ) {
        Text(
            text = (number + 1).toString(),
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color.Green)
                .padding(5.dp),
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center

        )
        Text(
            text = weightReps.weight,
            modifier = Modifier
                .weight(2f)
                .border(1.dp, Color.Green)
                .padding(5.dp),
            style = TextStyle(
                color = Color.Green,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = weightReps.reps,
            modifier = Modifier
                .weight(2f)
                .border(1.dp, Color.Green)
                .padding(5.dp),
            style = TextStyle(
                color = Color.Green,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center
        )

    }
}