package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout_univ

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutDate


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UiNameScreen(
    workoutDate: WorkoutDate,
    checking: Boolean,
    id: Int,
    clickDescription: (String) -> Unit,
    clickFood: (WorkoutDate) -> Unit,
    addList: (WorkoutDate) -> Unit
) {

    val check = mutableStateOf(workoutDate.checking)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .combinedClickable(
                onClick = {
                    if (checking) {
                        Log.d("LogUi", "add list")
                        addList(
                            WorkoutDate(
                                name = workoutDate.name,
                                equipment = workoutDate.equipment,
                                primaryMuscles = workoutDate.primaryMuscles,
                                secondaryMuscles = workoutDate.secondaryMuscles,
                                additionalPar = "",
                                checking = !check.value
                            ),
                        )
                        check.value = !check.value
                    } else {
                        clickFood(
                            WorkoutDate(
                                name = workoutDate.name,
                                equipment = workoutDate.equipment,
                                primaryMuscles = workoutDate.primaryMuscles,
                                secondaryMuscles = workoutDate.secondaryMuscles,
                                additionalPar = "",
                                checking = false
                            ),
                        )
                    }

                }
            ),

        colors = CardColors(
            Color.DarkGray,
            Color.DarkGray,
            Color.DarkGray,
            Color.DarkGray,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_food_bank_24),
                contentDescription = "Food name",
                modifier = Modifier.weight(0.5f)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = workoutDate.name,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.equipment)} ${workoutDate.equipment}",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.primary_muscles)} ${workoutDate.primaryMuscles}",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                if (!workoutDate.secondaryMuscles.trim().equals("null".trim())) {
                    Text(
                        text = "${stringResource(id = R.string.secondary_muscles)} ${workoutDate.secondaryMuscles}",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

            }
            Column(
                //modifier = Modifier.weight(0.2f)
            ) {
                if (id < 0) {
                    Checkbox(
                        checked = check.value,
                        onCheckedChange = {
                            addList(
                                WorkoutDate(
                                    name = workoutDate.name,
                                    equipment = workoutDate.equipment,
                                    primaryMuscles = workoutDate.primaryMuscles,
                                    secondaryMuscles = workoutDate.secondaryMuscles,
                                    additionalPar = "",
                                    checking = it
                                ),
                            )
                            check.value = it
                        }
                    )
                }
                IconButton(onClick = { clickDescription(workoutDate.name) }) {
                    Icon(painter = painterResource(id = R.drawable.icon_description), contentDescription = "Description",
                        tint = Color.White)
                }
            }
        }
    }
}