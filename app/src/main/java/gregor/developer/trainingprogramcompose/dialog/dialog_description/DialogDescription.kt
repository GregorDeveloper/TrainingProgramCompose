package gregor.developer.trainingprogramcompose.dialog.dialog_description

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutItem
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.utils.Routes


@Composable
fun DialogDescription(
    dialogDescriptionController: DialogDescriptionController,
  //  navController: NavHostController,
    onNavigate: (Boolean) -> Unit
) {
    if (dialogDescriptionController.openDialogDescription.value) {
        Dialog(
            onDismissRequest = {
                dialogDescriptionController.onDialogDescriptionEvent(DialogDescriptionEvent.OnCancel)
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Card(
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .border(1.dp, color = Color.Green, shape = RoundedCornerShape(15.dp)),
                backgroundColor = Color.DarkGray
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(
                            R.drawable.fitness_test
                            // dialogDescriptionController.workoutImage.value
                        ),
                        contentDescription = "Image description",
                    )
                    Text(
                        text = dialogDescriptionController.workoutName.value,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Green
                        ),
                        fontSize = 20.sp
                    )
                    Text(
                        text = dialogDescriptionController.workoutDescription.value,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Green
                        ),
                        fontSize = 20.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                onNavigate(false)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Gray,
                                contentColor = Color.Green
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            shape = CircleShape
                        ) {
                            Text(
                                text = "Cancel",
                            )
                        }

                        //  if (dialogDescriptionController.addWorkoutItem.value) {
                        Button(
                            onClick = {
                                dialogDescriptionController
                                    .onDialogDescriptionEvent(
                                        DialogDescriptionEvent
                                            .OnConfirm(dialogDescriptionController.workoutName.value)
                                    )
                                onNavigate(true)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Gray,
                                contentColor = Color.Green
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            shape = CircleShape
                        ) {
                            Text(
                                text = "Confirm",
                            )
                        }
                        // }
                    }
                }
            }
        }
    }
}