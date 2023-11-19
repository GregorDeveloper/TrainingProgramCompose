package gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent

//@Preview(showBackground = true)
@Composable
fun DialogWeightReps(
    dialogController: DialogWeightRepsController
) {
    if (dialogController.openDialog.value) {
        Dialog(
            onDismissRequest = { /*TODO*/ },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f),
                //.border(1.dp, color = Color.Green, shape = RoundedCornerShape(15.dp)),
                //  backgroundColor = Color.DarkGray
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Dialog Title"
                    )

                    TextField(
                        value = dialogController.weight.value,
                        //dialogController.editableText.value,
                        onValueChange = { weight ->
                        dialogController.weight.value = weight
//                            dialogController.onDialogEvent(
//                                DialogWeightRepsEvent.OnTextChangeWeight(
//                                    weight
//                                )
                          //  )
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.weight)
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(9.dp),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 16.sp
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = dialogController.reps.value,
                        onValueChange = { reps ->
                            dialogController.onDialogEvent(
                                DialogWeightRepsEvent.OnTextChangeWeight(
                                    reps
                                )
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.reps)
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(9.dp),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 16.sp
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                        // horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(id = android.R.string.cancel))
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(id = android.R.string.ok))
                        }
                    }
                }


            }
        }

    }
}