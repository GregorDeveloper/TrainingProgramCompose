package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.static_data.Date
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListEvent
//import gregor.developer.trainingprogramcompose.data.static_data.Month
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject
import java.time.Month

@HiltViewModel
class CalendarScreenViewModel @Inject constructor(
    private val repository: WorkOutListRepository,
) : ViewModel(), DialogController {
    private var localDate = LocalDate.now()

    override var dialogTitle = mutableStateOf("List name")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set
    override var choiceDialog = mutableStateOf("")
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
              //  cancelSwipe.value = true
            }

            is DialogEvent.OnConfirm -> {
                if (showEditableText.value) {
                //    onEvent(TrainingListEvent.OnItemSave)

                } else {
                    viewModelScope.launch {
                    //    listItem?.let { repository.deleteItem(it) }
                    }
                }
              //  cancelSwipe.value = true
                openDialog.value = false
            }

            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }

            else -> {}
        }
    }

    var itemsList: List<WorkoutListItem>? = mutableStateListOf()
    var itemsList2 = mutableStateOf<List<WorkoutListItem>>(emptyList())
    var workoutItem: WorkoutListItem? = null
    val listOfCurrentMonth = mutableStateOf<Date>(Date(
        "JANUARY",
        2000,
        listOf(
            DayTraining(
            1, false)),
        0
    ))
    val selectedDate = mutableStateOf("")
    val todayDate = mutableStateOf<Date>(getCurrentDate())
    val rows = mutableStateOf(0)
    val aspectRatio = mutableStateOf(1.3f)

    init {
        getDayOfMonth(localDate.lengthOfMonth())
    }

    fun onEvent(event: CalendarEvent) {
        when (event) {
            is CalendarEvent.ClickDay -> {
                if(listOfCurrentMonth.value.dayInMonth.get(event.day.day - 1).training == true) {
                    if (itemsList2.value.isEmpty()) {
                        selectedDate.value = selectedDateToString(event.day)
                        viewModelScope.launch {
                            itemsList2.value = getAllItemsByDate(dateForDB(event.day.day))
                        }
                    } else {
                        selectedDate.value = ""
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

            }
            is CalendarEvent.AddWorkout -> {

            }
            is CalendarEvent.ClickWorkout -> {

            }
            is CalendarEvent.DeleteTrainingInDay -> {

            }
            is CalendarEvent.DeleteWorkOut -> {

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

    private fun getCurrentDate(): Date {
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
                        checkTrainingByDate(dateForDB(i))
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
        val list  = getAllItemsByDate(date)
        if (list.isNotEmpty() == true) return true else return false
    }

    private suspend fun getAllItemsByDate(date: String): List<WorkoutListItem>{
        return date.let { repository.getAllItemsByDate(it) }
    }

    private fun lastNextMonth(modifier: Boolean) {
        localDate = if (modifier) {
            localDate.minusMonths(1)
        } else {
            localDate.plusMonths(1)
        }
        listOfCurrentMonth.value = getCalendar()

    }

    private fun selectedDateToString(dayTraining: DayTraining): String{
        return dayTraining.day.toString() + "." + selectedMonth() + "." + localDate.year.toString()
    }

    private fun dateForDB(day: Int): String{
        var zero = ""
        if (day in 1..9) {
            zero = "0"
        }
       val date = zero + day.toString() + "." + selectedMonth() + "." + localDate.year.toString()
        return date
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

    private fun selectedMonth(): String {
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
}