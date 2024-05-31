package gregor.developer.trainingprogramcompose.screen.title_date

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.calendar_screen.UiDateList
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ.WeightRepsUnivEvent
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ.WeightRepsUnivViewModel
import gregor.developer.trainingprogramcompose.utils.getCurrentDate


@Composable
fun TitleDate(
    viewModel: WeightRepsUnivViewModel,
    openChangeDate: Boolean,
    date: String,
    onLastOrNextTraining: (LastOrNextDateEvent) -> Unit
) {
    var expanded by remember {
        mutableStateOf(viewModel.openDropdownMenu)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = if (openChangeDate) Arrangement.SpaceBetween else Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (openChangeDate) {
            IconButton(onClick = {
                viewModel.onLastOrNextEvent(LastOrNextDateEvent.LastTraining)
            }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_left
                    ),
                    contentDescription = "left",
                    tint = Color.Green,
                )
            }
        }

        Row {
            Text(
                text = date,
                fontWeight = FontWeight.SemiBold,
                color = Color.Green,
                fontSize = 25.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .wrapContentSize()
                    .clickable {
                        viewModel.onEvent(WeightRepsUnivEvent.OpenDialogDate)
                    },
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 120.dp, height = 120.dp)
                ) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        if (viewModel.listDate.value?.first()?.date != viewModel.getCurrentDate()) {
                            item {
                                Text(
                                    text = viewModel.getCurrentDate(),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .clickable {
                                            viewModel.onLastOrNextEvent(
                                                LastOrNextDateEvent.ClickCurrentDate(
                                                    viewModel.getCurrentDate()
                                                )
                                            )
                                        }
                                        .padding(3.dp),
                                )
                            }
                        }
                        itemsIndexed(
                            viewModel.listDate.value!!
                        ) { index, item ->
                            UiDateList(item = item) { event ->
                                onLastOrNextTraining(event)
                            }
                        }
                    }
                }

            }

        }



        if (openChangeDate) {
            IconButton(onClick = {
                viewModel.onLastOrNextEvent(LastOrNextDateEvent.NextTraining)
            }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_right
                    ),
                    contentDescription = "left",
                    tint = Color.Green,
                    modifier = Modifier.weight(0.2f)
                )
            }
        }
    }
}