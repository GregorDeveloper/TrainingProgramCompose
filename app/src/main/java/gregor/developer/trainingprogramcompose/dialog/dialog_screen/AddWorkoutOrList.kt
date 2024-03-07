package gregor.developer.trainingprogramcompose.dialog.dialog_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.utils.Routes


@Composable
fun AddWorkoutOrList(dialogController: DialogController) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Date Title",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Green
            )
        )
        TextButton(
            onClick = { dialogController.onDialogEvent(DialogEvent.AddList(Routes.WORKOUT_LIST)) },
            modifier = Modifier
        ) {
            Text(
                text = "Add workout",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.LightGray
                ),
                textAlign = TextAlign.Start
            )
        }
        TextButton(
            onClick = { dialogController.onDialogEvent(DialogEvent.AddList(Routes.TRAINING_LIST)) },
            modifier = Modifier
        ) {
            Text(
                text = "Add training list",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.LightGray
                ),
                textAlign = TextAlign.End
            )
        }
    }

}