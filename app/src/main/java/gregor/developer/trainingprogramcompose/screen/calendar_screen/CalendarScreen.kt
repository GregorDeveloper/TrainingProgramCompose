package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.screen.workout_screen.user_workout.UiUserWorkOutScreen
import gregor.developer.trainingprogramcompose.utils.Routes
import gregor.developer.trainingprogramcompose.utils.UiEvent

@Composable
fun CalendarScreen(
    viewModel: CalendarScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                else -> {

                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { viewModel.onEvent(CalendarEvent.ChangeMonth(true)) }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_left
                    ),
                    contentDescription = "left",
                    tint = Color.Green,
                )
            }
            Text(
                text = viewModel.listOfCurrentMonth.value.month,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 40.sp,
            )
            IconButton(onClick = { viewModel.onEvent(CalendarEvent.ChangeMonth(false)) }) {
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
        Calendar(
            dateList = viewModel.listOfCurrentMonth.value,
            onDayClick = { day ->
                viewModel.onEvent(CalendarEvent.ClickDay(day))
            },
            todayDate = viewModel.todayDate.value,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(viewModel.aspectRatio.value),
            rows = viewModel.rows.value
        )

        if (viewModel.openTitle.value) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                backgroundColor = Color.DarkGray
            ) {
                TitleWorkoutCalendar(viewModel)

            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(viewModel.itemsList2.value, key = { _, listItem ->
                listItem.hashCode()
            }) { index, item ->
                UiUserWorkOutScreen(item) { event ->
                    onNavigate(
                        event
                    )
                    //Перейти к концу списка
                }
            }
        }
    }
}


@Composable
fun TitleWorkoutCalendar(
    viewModel: CalendarScreenViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp)
        ) {
            Text(text = viewModel.selectedDate.value)
        }



        Row {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Add workout or list"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                TextButton(
                    onClick = {
                        viewModel.onEvent(
                            CalendarEvent.AddWorkout(
                                Routes.WORKOUT_LIST + "/${viewModel.selectedDate.value}"
                            )
                        )
                    },
                    modifier = Modifier.padding(3.dp)
                ) {
                    Text(
                        text = "Add workout",
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
                Divider()
                TextButton(
                    onClick = {
                        viewModel.onEvent(
                            CalendarEvent.AddListWorkout(
                                Routes.TRAINING_LIST + "/${viewModel.selectedDate.value}"
                            )
                        )
                    },
                    modifier = Modifier.padding(3.dp)
                ) {
                    Text(
                        text = "Add list training",
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
            }
            IconButton(
                onClick = { /*TODO*/ },
                // modifier = Modifier.padding(3.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete_icon),
                    contentDescription = "Delete training"
                )
            }
        }
    }
}