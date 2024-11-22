package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.rememberNavController
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.MainDialog
import gregor.developer.trainingprogramcompose.navigation.CalendarNavigationGraph
import gregor.developer.trainingprogramcompose.screen.calendar_screen.ListWorkout.TitleFoodList
import gregor.developer.trainingprogramcompose.screen.calendar_screen.calendar_bottom.CalendarBottomNavItem
import gregor.developer.trainingprogramcompose.screen.title_date.TitleDate
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
    var route = remember {
        mutableStateOf(Routes.FOOD_SCREEN)
    }
    val conf = LocalConfiguration.current
    val width = conf.screenWidthDp
    val listFlow = viewModel.listWorkoutFlow?.collectAsState(initial = emptyList())
    val navController = rememberNavController()
    val context = LocalContext.current

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

                is UiEvent.ShowToast -> {
                    val date = context.getString(R.string.toast_calendar) + " " + uiEvent.date
                    Toast.makeText(context, date, Toast.LENGTH_SHORT).show()
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .padding(3.dp),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(9.dp),
        ) {
            TitleDate(
                openChangeDate = true,
                openDropMenu = viewModel.openDropdownMenu,
                openItemCurrentDate = false,
                dateItem = viewModel.todayDate,
                modifier = Modifier.fillMaxWidth(),
                list = emptyList(),
                currentDate = viewModel.listOfCurrentMonth.value.month,
                selectedDate = viewModel.listOfCurrentMonth.value.month + " "
                        // + "\n"
                        + viewModel.listOfCurrentMonth.value.year,
            ) { event ->
                viewModel.lastOrNextMonth(event)
            }
        }
        Calendar(
            dateList = viewModel.listOfCurrentMonth,
            onDayClick = { day ->
                viewModel.onEvent(CalendarEvent.ClickDay(day))
            },
            todayDate = viewModel.todayDate.value,
            modifier = Modifier
                .padding(
                    start = getPadding(viewModel.calendarScale.value),
                    end = getPadding(viewModel.calendarScale.value)
                )
                .aspectRatio(
                    1.75f
                    // viewModel.aspectRatio.value
                ),
            rows = viewModel.rows.value,
            canvasPar = viewModel.selectedDate.value
        ) { canvasPar ->
            viewModel.onEvent(CalendarEvent.SaveCanvasParametr(canvasPar))
        }
        Column(modifier = Modifier.weight(1f)) {
            if (viewModel.openTitle.value) {
                CalendarBottomNavItem(navController) { currentRoute ->
                    route.value = currentRoute
                    viewModel.onEvent(CalendarEvent.SaveCurrentRoute(currentRoute))
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp),
                    backgroundColor = Color.DarkGray
                ) {
                    Column {
                        TitleWorkoutCalendar(viewModel, context, route)
                    }
                }
            }
            CalendarNavigationGraph(navController, viewModel)
        }

    }
    MainDialog(viewModel)
}


@Composable
fun TitleWorkoutCalendar(
    viewModel: CalendarScreenViewModel,
    context: Context,
    route: MutableState<String>
) {
    var sumParameterFood by remember {
        mutableStateOf(viewModel.sumFoodParameter)
    }
    Column {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var expanded by remember {
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            ) {
                Text(
                    text = viewModel.selectedDate.value.date,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                )
            }
            Spacer(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .padding(5.dp)
            )
            if (viewModel.openTitleButton.value) {
                Row {
                    IconButton(onClick = {
                        when (viewModel.currentRoute) {
                            Routes.LIST_WORKOUT -> {
                                expanded = true
                            }

                            Routes.FOOD_SCREEN -> {
                                viewModel.onEvent(
                                    CalendarEvent.AddWorkout(
                                        Routes.FOOD_SCREEN +
                                                "/${viewModel.selectedDate.value.date}" +
                                                "/${-1}"
                                    )
                                )
                            }

                            else -> {

                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_icon),
                            contentDescription = "Add workout or list",
                            tint = Color.White,
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
                                        Routes.WORKOUT_LIST +
                                                "/${viewModel.selectedDate.value.date}" +
                                                "/${-1}"
                                    )
                                )
                                expanded = false
                            },
                            modifier = Modifier.padding(3.dp)
                        ) {
                            Text(
                                text = "Add workout",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White
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
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            )
                        }
                    }

                    IconButton(
                        onClick = {
                            if (viewModel.listWorkoutFlow != null) {
                                viewModel.onEvent(
                                    CalendarEvent.OpenDialogWorkout(
                                        WorkoutListItem(
                                            null,
                                            "",
                                            viewModel.selectedDate.value.date,
                                            0
                                        ),
                                        Routes.DIALOG_DELETE_TRAINING,
                                        context.getString(R.string.delete_all_workout_for)
                                    )
                                )
                            }
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete_icon),
                            contentDescription = "Delete training",
                            tint = Color.White
                        )
                    }
                }
            }
        }
        if (Routes.FOOD_SCREEN == route.value) {
            TitleFoodList(sumParameterFood = sumParameterFood)
        }
    }


}

fun getPadding(scale: Boolean): Dp {
    return if (scale) 3.dp else 30.dp
}