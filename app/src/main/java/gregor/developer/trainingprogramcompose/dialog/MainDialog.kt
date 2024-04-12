package gregor.developer.trainingprogramcompose.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import gregor.developer.trainingprogramcompose.dialog.dialog_screen.AddTraining
import gregor.developer.trainingprogramcompose.dialog.dialog_screen.AddWorkoutOrList
import gregor.developer.trainingprogramcompose.utils.Routes


@Composable
fun MainDialog(
    dialogController: DialogController
) {
    if (dialogController.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            title = null,
            text = {
                Column(
                   modifier = Modifier.wrapContentWidth()
                ) {
//                    when (dialogController.choiceDialog.value) {
//                        RoutesDialog.ADD_TRAINING -> {
                            AddTraining(dialogController)
//                        }
//                        RoutesDialog.ADD_WORKOUT_OR_LIST -> {
//                            AddWorkoutOrList(dialogController)
//                        }
//                        else -> {
//
//                        }
                  //  }
                }
            },
            confirmButton = {
             //   if(dialogController.choiceDialog.value == RoutesDialog.ADD_TRAINING){
                    TextButton(
                        onClick = {
                            dialogController.onDialogEvent(DialogEvent.OnConfirm)
                        }
                    ) {
                        Text(text = "Ok")
                    }
              //  }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        dialogController.onDialogEvent(DialogEvent.OnCancel)
                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            backgroundColor = Color.DarkGray
        )
    }
}

