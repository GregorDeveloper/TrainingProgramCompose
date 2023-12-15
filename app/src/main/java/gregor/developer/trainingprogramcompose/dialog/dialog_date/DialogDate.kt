package gregor.developer.trainingprogramcompose.dialog.dialog_date

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.OldWeightRepsScreen.UiDateItem

@SuppressLint("SuspiciousIndentation")

@Composable
fun DialogDate(
    dialogDateController: DialogDateController
) {
    if (dialogDateController.openDialog.value) {
        Dialog(onDismissRequest = {
            dialogDateController.onDialogDateEvent(DialogDateEvent.OnCancel)
        }) {
            Card(
                backgroundColor = Color.DarkGray,
                modifier = Modifier.height(350.dp)
            ) {


                Column(
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.selected_date),
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(0.2f),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Green
                        )
                    )
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        itemsIndexed(
                             dialogDateController.listDate
                        ) { index, item ->
                            UiDateItem(date = item) {
                                 dialogDateController.onDialogDateEvent(DialogDateEvent.OnClickItem(index - 1))
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.weight(0.2f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = {
                                  dialogDateController.onDialogDateEvent(DialogDateEvent.OnCancel)
                            },
                        ) {
                            Text(
                                text = "Cancel",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.Green,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                }
            }
        }
    }
}

