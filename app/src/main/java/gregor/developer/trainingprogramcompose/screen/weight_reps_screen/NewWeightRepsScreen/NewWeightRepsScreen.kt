package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightReps
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListViewModel

//@Preview(showBackground = true)
@Composable
fun NewWeightRepsScreen(
    workoutName: String,
    viewModel: NewWeightRepsViewModel = hiltViewModel(),
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (card, iconButton) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 2.dp,
                    end = 2.dp
                )
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = viewModel.item.value?.workOutName ?: "Name",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Number",
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color.Green),
                        style = TextStyle(
                            color = Color.Green,
                            fontSize = 18.sp
                        ),
                        textAlign = TextAlign.Center

                    )
                    Text(
                        text = "Weight",
                        modifier = Modifier
                            .weight(2f)
                            .border(1.dp, Color.Green),
                        style = TextStyle(
                            color = Color.Green,
                            fontSize = 18.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Reps",
                        modifier = Modifier
                            .weight(2f)
                            .border(1.dp, Color.Green),
                        style = TextStyle(
                            color = Color.Green,
                            fontSize = 18.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    itemsIndexed(
                        viewModel.items
                    ) { index, item ->
                        UiNewWeightRepsScreen(item, index + 1){event ->
                            //viewModel.onEvent(event)
                        }
                    }
                }
            }
        }
        IconButton(onClick = {
            viewModel.onEvent(NewWeightRepsEvent.OnShowDialog)
        },
            modifier = Modifier
                .constrainAs(iconButton) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(
                    end = 10.dp,
                    bottom = 60.dp
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_icon_48),
                contentDescription = "add weight, reps",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.DarkGray)
            )

        }
    }

    DialogWeightReps(viewModel)

}