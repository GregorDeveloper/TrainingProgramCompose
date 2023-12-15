package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.OldWeightRepsScreen

import android.widget.ImageButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_date.DialogDate
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen.UiNewWeightRepsScreen


@Composable
fun OldWeightRepsScreen(
    viewModel: OldWeightRepsViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = viewModel.workoutName ?: "Error",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp))
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                viewModel.onEvent(OldWeightRepsEvent.previousItemList)
            },
                modifier = Modifier.weight(0.5f),) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "left",
                    tint = Color.Green
                )
            }

            TextButton(onClick = { viewModel.onEvent(OldWeightRepsEvent.openDialogDate)},
                modifier = Modifier.weight(1f),) {
                Text(text = viewModel.date.value,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp))
            }


            IconButton(onClick = {
                viewModel.onEvent(OldWeightRepsEvent.nextItemList)
            },
                modifier = Modifier.weight(0.5f)) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = "right",
                    tint = Color.Green)
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(100.dp)
        ){
            itemsIndexed(viewModel.itemsList){index, item ->
                UiNewWeightRepsScreen(weightReps = item ?: WeightRepsWorkoutItem(
                    0,
                    "Null",
                    "Null",
                    "Null",
                    "Null",
                ), number = index + 1){

                }
            }
        }
    }
    DialogDate(viewModel)
}