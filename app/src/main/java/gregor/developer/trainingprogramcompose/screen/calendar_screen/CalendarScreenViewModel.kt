package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.static_data.Date
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.screen.calendar_screen.data.CanvasPar
import gregor.developer.trainingprogramcompose.utils.UiEvent
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.forEach
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
) : ViewModel() {
    private var localDate = LocalDate.now()

    var listFlow: Flow<List<WorkoutListItem>>? = null //Flow тест
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    var itemsList2 = mutableStateOf<List<WorkoutListItem>>(emptyList())
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

    val selectedDate = mutableStateOf<CanvasPar>(CanvasPar(Offset.Zero, 0.0f, ""))
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
                    openTitle(selectedDateToString(event.day, localDate))
                    if (listOfCurrentMonth.value.dayInMonth.get(event.day.day - 1).training == true) {
                        viewModelScope.launch {
                            listFlow = getAllItemsByDateFlow(event.day.day.toString())
                            listFlow?.collect { list -> Log.d("LogFlow", list.size.toString()) }
                            itemsList2.value =
                                getAllItemsByDate(dateForDB(event.day.day, localDate))
                        }
                    } else {

                        viewModelScope.launch {
                            listFlow = emptyFlow()
                            listFlow?.collect { list -> Log.d("LogElse", list.size.toString()) }
                        }
                        itemsList2.value = emptyList()
                    }
                }
            }

            is CalendarEvent.ChangeMonth -> {
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

            }

            is CalendarEvent.DeleteTrainingInDay -> {

            }

            is CalendarEvent.DeleteWorkOut -> {

            }

            else -> {}
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

        // listFlow = getAllItemsByDateFlow(date)

        return if (list.isNotEmpty() == true) true else false
    }

    private fun getAllItemsByDateFlow(date: String): Flow<List<WorkoutListItem>> {
        return date.let { repository.getAllItemsByDateFlow(dateForDB(it.toInt(), localDate)) }
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
        var zero = ""
        if (day in 1..9) {
            zero = "0"
        }
        val date =
            zero + day.toString() + "." + selectedMonth(localDate) + "." + localDate.year.toString()
        return date
    }

    private fun resetSelectedDay() {
        selectedDate.value.date = ""
        itemsList2.value = emptyList()
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

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}