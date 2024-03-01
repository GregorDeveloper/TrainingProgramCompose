package gregor.developer.trainingprogramcompose.dialog.dialog_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent

@Composable
fun AddTraining(dialogController: DialogController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = dialogController.dialogTitle.value,
            style = TextStyle(
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (dialogController.showEditableText.value) {
            TextField(
                value = dialogController.editableText.value,
                onValueChange = {
                    dialogController.onDialogEvent(DialogEvent.OnTextChange(it))
                },
                label = {
                    Text(
                        text = stringResource(R.string.training_name)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(9.dp),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
    }
}