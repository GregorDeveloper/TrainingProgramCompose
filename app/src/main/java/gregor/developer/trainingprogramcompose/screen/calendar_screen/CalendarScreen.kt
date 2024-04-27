package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.swipe_to_dismiss.ParametrSwipeItem
import gregor.developer.trainingprogramcompose.dialog.MainDialog
import gregor.developer.trainingprogramcompose.screen.swipe_screen.SwipeItem
import gregor.developer.trainingprogramcompose.utils.Routes
import gregor.developer.trainingprogramcompose.utils.UiEvent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalendarScreen(
    trainingUpdate: Boolean,
    viewModel: CalendarScreenViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onNavigate: (String) -> Unit
) {
    val workoutListFlow = viewModel.listFlow?.collectAsState(initial = emptyList())

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    viewModel.cancelSwipe.value = true
                    if (viewModel.selectedDate.value.date != "" && trainingUpdate) {
                        val date = viewModel.getTwoSymbol()
                        viewModel.listOfCurrentMonth.value.dayInMonth.get(date - 1).training = true
                        viewModel.onEvent(CalendarEvent.GetTraining(viewModel.selectedDate.value.date))
                    }
                }
                else -> {
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
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
            IconButton(onClick = {
                viewModel.onEvent(CalendarEvent.ChangeMonth(true))
            }) {
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
            IconButton(onClick = {
                viewModel.onEvent(CalendarEvent.ChangeMonth(false))
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
            rows = viewModel.rows.value,
            canvasPar = viewModel.selectedDate.value
        ) { canvasPar ->
            viewModel.onEvent(CalendarEvent.SaveCanvasParametr(canvasPar))
        }

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

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            if (workoutListFlow != null) {
                itemsIndexed(workoutListFlow.value,
                    key = { _,
                            listItem ->
                        listItem.hashCode()
                    }) { index, item ->
                    val dismissThreshold = if(viewModel.openTitle.value)0.5f else 1.5f

                    val currentFraction = remember{ mutableStateOf(0f) }
                    val state = rememberDismissState(
                        confirmStateChange = { dismissValue ->
                            when (dismissValue) {
                                DismissValue.DismissedToStart -> {
                                    if(currentFraction.value >= dismissThreshold && currentFraction.value < 1.0f){
                                        viewModel.cancelSwipe.value = false
                                        viewModel.onEvent(CalendarEvent.OpenDialog(item, Routes.DIALOG_DELETE_WORKOUT))
                                        true
                                    }else false
                                }

                                DismissValue.DismissedToEnd -> {
                                    if(currentFraction.value >= dismissThreshold && currentFraction.value < 1.0f) {
                                        viewModel.cancelSwipe.value = false
                                        viewModel.onEvent(
                                            CalendarEvent.OpenDialog(
                                                item,
                                                Routes.DIALOG_EDIT
                                            )
                                        )
                                        true
                                    }else false
                                }

                                DismissValue.Default -> {
                                    viewModel.cancelSwipe.value = false
                                    true
                                }
                            }
                        }
                    )
                    SwipeToDismiss(
                        state = state,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 3.dp,
                                start = 3.dp,
                                end = 3.dp
                            ),
                        dismissThresholds = {
                                            FractionalThreshold(dismissThreshold)
                        },
                        background = {

                            currentFraction.value = state.progress.fraction
                            val parametersSwipeItem =
                                state.dismissDirection?.let {
                                    state.dismissDirection
                                    ParametrSwipeItem(it)
                                }
                            if (parametersSwipeItem != null) {
                                if (viewModel.cancelSwipe.value) {
                                    LaunchedEffect(key1 = viewModel.cancelSwipe.value) {
                                        state.reset()
                                        viewModel.cancelSwipe.value = false
                                    }
                                }
                                SwipeItem(parametersSwipeItem)
                            }
                        },
                        dismissContent = {
                            UiWorkOutScreen(item) { event ->
                                onNavigate(
                                    event
                                )
                                //Перейти к концу списка!!!!
                            }
                        },

                    )
                    Divider()
                }
            }
        }
    }
    MainDialog(viewModel)
}


@Composable
fun TitleWorkoutCalendar(
    viewModel: CalendarScreenViewModel,
) {
    Log.d("LogOnResume", viewModel.selectedDate.value.date + " title")
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
            Text(text = viewModel.selectedDate.value.date)
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
                                Routes.WORKOUT_LIST + "/${viewModel.selectedDate.value.date}" + "/${-1}"
                            )
                        )
                        expanded = false
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
                                Routes.TRAINING_LIST + "/${viewModel.selectedDate.value.date}"
                            )
                        )
                        expanded = false
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
                onClick = {
                    if (viewModel.listFlow != null) {
                        viewModel.onEvent(CalendarEvent.OpenDialog(
                            WorkoutListItem(
                                null,
                                "",
                                viewModel.selectedDate.value.date,
                                0
                            ),
                            Routes.DIALOG_DELETE_TRAINING
                        )
                        )
                    }
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete_icon),
                    contentDescription = "Delete training"
                )
            }
        }
    }
}