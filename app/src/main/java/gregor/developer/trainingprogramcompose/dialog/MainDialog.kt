package gregor.developer.trainingprogramcompose.dialog

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_date.DialogDate
import gregor.developer.trainingprogramcompose.dialog.dialog_screen.AddTraining
import gregor.developer.trainingprogramcompose.dialog.dialog_screen.AddWorkoutOrList
import gregor.developer.trainingprogramcompose.utils.Routes


@Composable
fun MainDialog(
    dialogController: DialogController
) {
    val height = if (dialogController.addTraining.value) 100
    else if (dialogController.selectedMonthYear.value) 200
    else 50
    if (dialogController.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            title = null,
            text = {
                Column(
                    modifier = Modifier.size(width = 350.dp, height = height.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = dialogController.dialogTitle.value,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Alignment.Center)
                        )
                        if (dialogController.selectedMonthYear.value) {
                            IconButton(
                                onClick = { dialogController.onDialogEvent(DialogEvent.OnReturnCurrentMonth) },
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterEnd)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.return_current_month),
                                    contentDescription = "return current month",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    if (dialogController.addTraining.value) {
                        AddTraining(dialogController)
                    } else if (dialogController.selectedMonthYear.value) {
                        DialogDate(dialogController)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        dialogController.onDialogEvent(DialogEvent.OnConfirm)
                    }
                ) {
                    Text(text = "Ok")
                }
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

