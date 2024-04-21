package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.static_data.Date
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.screen.calendar_screen.data.CanvasParametr
import gregor.developer.trainingprogramcompose.utils.Routes
import gregor.developer.trainingprogramcompose.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow
//import gregor.developer.trainingprogramcompose.data.static_data.Month
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject
import java.time.Month
import java.time.format.DateTimeFormatter

@HiltViewModel
class CalendarScreenViewModel @Inject constructor(
    private val repository: WorkOutListRepository,
) : ViewModel(), DialogController {
    override var dialogTitle = mutableStateOf("List name")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    private var localDate = LocalDate.now()

    var listFlow: Flow<List<WorkoutListItem>>? = null //Flow тест
    private var listItem: WorkoutListItem? = null
    private var choiceDialog: String? = null
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    var cancelSwipe = mutableStateOf(false)
    val listOfCurrentMonth = mutableStateOf<Date>(
        Date(
            "JANUARY",
            2000,
            listOf(
                DayTraining(
                    1, false
                )
            ),
            0
        )
    )
    val openTitle = mutableStateOf(false)

    val selectedDate = mutableStateOf<CanvasParametr>(CanvasParametr(Offset.Zero, 0.0f, ""))
    val todayDate = mutableStateOf<Date>(getCurrentDateList())
    val rows = mutableStateOf(0)
    val aspectRatio = mutableStateOf(1.3f)

    init {
        getDayOfMonth(localDate.lengthOfMonth())
    }

    fun onEvent(event: CalendarEvent) {
        when (event) {
            is CalendarEvent.ClickDay -> {
                if (selectedDateToString(event.day, localDate).equals(selectedDate.value.date)) {
                    resetSelectedDay()
                } else {
                    selectedDate.value.date = selectedDateToString(event.day, localDate)
                    openTitle(selectedDate.value.date)
                    if (listOfCurrentMonth.value.dayInMonth.get(event.day.day - 1).training == true) {
                        viewModelScope.launch {
                            listFlow = getAllItemsByDateFlow(event.day.day.toString())
                        }
                    } else {
                        viewModelScope.launch {
                            listFlow = null
                        }
                    }
                }
            }

            is CalendarEvent.ChangeMonth -> {
                selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "")
                lastNextMonth(event.change)
                if (listOfCurrentMonth.value.dayOfWeek >= 6) {
                    rows.value = 1
                    aspectRatio.value = 1.1f
                } else if (listOfCurrentMonth.value.dayOfWeek >= 5 && listOfCurrentMonth.value.dayInMonth.size >= 31) {
                    rows.value = 1
                    aspectRatio.value = 1.1f
                } else {
                    rows.value = 0
                    aspectRatio.value = 1.3f
                }
            }

            is CalendarEvent.AddListWorkout -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }

            is CalendarEvent.AddWorkout -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }

            is CalendarEvent.ClickWorkout -> {
                //  sendUiEvent(UiEvent.Navigate())
            }

            is CalendarEvent.DeleteTrainingInDay -> {
                openDialog.value = true
                dialogTitle.value = "Delete all workout for ${event.date}"
            }

            is CalendarEvent.DeleteWorkOut -> {

            }

            is CalendarEvent.OpenDialog -> {
                openDialog.value = true
                listItem = event.workout
                var title = ""
                when (event.dialog) {
                    Routes.DIALOG_DELETE_TRAINING -> {
                        title = "Delete all workout for ${event.workout.date}"
                        choiceDialog = event.dialog
                    }

                    Routes.DIALOG_DELETE_WORKOUT -> {
                        title = "Delete ${event.workout.workoutName}"
                        choiceDialog = event.dialog
                    }

                    Routes.DIALOG_EDIT -> {
                        title = "Replace ${event.workout.workoutName}"

                        choiceDialog = event.dialog
                    }
                }

                dialogTitle.value = title
            }

            is CalendarEvent.GetTraining -> {
                    listFlow = getAllItemsByDateFlow(event.date)
            }

            is CalendarEvent.SaveCanvasParametr -> {
                selectedDate.value = CanvasParametr(
                    offset = event.canvasPar.offset,
                    radios = event.canvasPar.radios,
                    date = selectedDateToString(
                        DayTraining(event.canvasPar.date.toInt()),
                        localDate
                    )
                )

            }

            is CalendarEvent.OpenDialog -> TODO()
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnConfirm -> {

                    when (choiceDialog) {
                        Routes.DIALOG_DELETE_WORKOUT -> {
                            viewModelScope.launch {
                                if (listItem != null) repository.deleteItem(listItem!!)
                                listFlow?.collect{

                                }
                            }
                        }
                        Routes.DIALOG_DELETE_TRAINING -> {
                            viewModelScope.launch {
                                listFlow?.collect { list ->
                                    list.forEach { item ->
                                        repository.deleteItem(item)
                                    }
                                }
                            }
                            listOfCurrentMonth.value.dayInMonth
                                .get(getTwoSymbol() - 1)
                                .training = false
                        }
                        //Ошибка при добавлении тренировки
                        Routes.DIALOG_EDIT -> {
                            sendUiEvent(UiEvent.Navigate(
                                Routes.WORKOUT_LIST +
                                        "/${selectedDate.value.date}" +
                                        "/${listItem?.id}"))
                        }
                    }
                listOfCurrentMonth.value.dayInMonth
                    .get(getTwoSymbol() - 1)
                    .training = false
                choiceDialog = null
                listItem = null
                openDialog.value = false
            }

            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }

            else -> {

            }
        }
    }

    private fun openTitle(day: String) {
        val formatter = DateTimeFormatter.ofPattern("d.MM.yyyy")
        val day2 = selectedDateToString(DayTraining(LocalDate.now().dayOfMonth), LocalDate.now())
        val currentDay = LocalDate.parse(day, formatter)
        val selectedDay = LocalDate.parse(day2, formatter)
        when {
            selectedDay > currentDay -> {
                openTitle.value = false
            }

            selectedDay == currentDay -> {
                openTitle.value = true
            }

            selectedDay < currentDay -> {
                openTitle.value = true
            }
        }
    }

    private fun getCalendar(): Date {
        return Date(
            localDate.month.toString(),
            localDate.year,
            getDayOfMonth(localDate.lengthOfMonth()),
            selectedOfSetDayOfWeek(localDate),
        )
    }

    private fun getCurrentDateList(): Date {
        return Date(
            localDate.month.toString(),
            localDate.year,
            listOf(DayTraining(localDate.dayOfMonth)),
            selectedOfSetDayOfWeek(localDate),
        )
    }

    private fun getDayOfMonth(daysInMonth: Int): List<DayTraining> {
        val listDays = mutableListOf<DayTraining>()
        viewModelScope.launch {
            for (i in 1..daysInMonth) {
                listDays.add(
                    DayTraining(
                        i,
                        checkTrainingByDate(dateForDB(i, localDate))
                    )
                )
            }
            listOfCurrentMonth.value = Date(
                localDate.month.toString(),
                localDate.year,
                listDays,
                selectedOfSetDayOfWeek(localDate),
            )

        }
        return listDays
    }


    private suspend fun checkTrainingByDate(date: String): Boolean {
        val list = getAllItemsByDate(date)
        return if (list.isNotEmpty() == true) true else false
    }

    fun getAllItemsByDateFlow(date: String): Flow<List<WorkoutListItem>> {
        val dt: String
        if (date.length > 3) {
            Log.d("LogDateLenght", "lenght > 3")
            dt = date
        } else {
            dt = dateForDB(date.toInt(), localDate)
            Log.d("LogDateLenght", "lenght < 3")
        }
        return repository.getAllItemsByDateFlow(dt)
    }


    private suspend fun getAllItemsByDate(date: String): List<WorkoutListItem> {
        return date.let { repository.getAllItemsByDate(it) }
    }

    private fun lastNextMonth(modifier: Boolean) {
        localDate = if (modifier) {
            localDate.minusMonths(1)
        } else {
            localDate.plusMonths(1)
        }
        listOfCurrentMonth.value = getCalendar()
        resetSelectedDay()
    }

    private fun selectedDateToString(dayTraining: DayTraining, localDate: LocalDate): String {
        return dayTraining.day.toString() + "." + selectedMonth(localDate) + "." + localDate.year.toString()
    }

    private fun dateForDB(day: Int, localDate: LocalDate): String {
        val zero = ""
//        if (day in 1..9) {
//            zero = "0"
//        }
        val date =
            zero + day.toString() + "." + selectedMonth(localDate) + "." + localDate.year.toString()

        return date
    }

    private fun resetSelectedDay() {
        listFlow = null
        selectedDate.value.date = ""
        openTitle.value = false
    }

    private fun selectedOfSetDayOfWeek(localDate: LocalDate): Int {
        val localDayOfWeek = LocalDate.of(localDate.year, localDate.month, 1)
        return when (localDayOfWeek.dayOfWeek) {
            DayOfWeek.MONDAY -> 0
            DayOfWeek.TUESDAY -> 1
            DayOfWeek.WEDNESDAY -> 2
            DayOfWeek.THURSDAY -> 3
            DayOfWeek.FRIDAY -> 4
            DayOfWeek.SATURDAY -> 5
            DayOfWeek.SUNDAY -> 6
            else -> -1

        }
    }

    private fun selectedMonth(localDate: LocalDate): String {
        return when (localDate.month) {
            Month.JANUARY -> "01"
            Month.FEBRUARY -> "02"
            Month.MARCH -> "03"
            Month.APRIL -> "04"
            Month.MAY -> "05"
            Month.JUNE -> "06"
            Month.JULY -> "07"
            Month.AUGUST -> "08"
            Month.SEPTEMBER -> "08"
            Month.OCTOBER -> "10"
            Month.NOVEMBER -> "11"
            Month.DECEMBER -> "12"
            else -> "011"

        }
    }

    fun getTwoSymbol(): Int {
        val date = selectedDate.value.date.substring(0, selectedDate.value.date.indexOf("."))
        return date.toInt()
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}