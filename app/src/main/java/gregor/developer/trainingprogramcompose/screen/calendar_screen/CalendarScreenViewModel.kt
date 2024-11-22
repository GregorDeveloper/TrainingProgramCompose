package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.CalculationCalorieRepository
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.trainingprogramcompose.data.static_data.Date
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.screen.calendar_screen.data.CanvasParametr
import gregor.developer.trainingprogramcompose.screen.swipe_screen.SwipeToDismissController
import gregor.developer.trainingprogramcompose.screen.title_date.LastOrNextDateEvent
import gregor.developer.trainingprogramcompose.utils.Routes
import gregor.developer.trainingprogramcompose.utils.UiEvent
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject
import java.time.Month
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale

@HiltViewModel
class CalendarScreenViewModel @Inject constructor(
    private val repository: WorkOutListRepository,
    private val repositoryFood: CalculationCalorieRepository
) : ViewModel(), DialogController, SwipeToDismissController {


    override var addTraining = mutableStateOf(false)
        private set
    override var selectedMonthYear = mutableStateOf(false)
        private set

    override var listMonth = mutableStateOf(
        listOf(
            Month.JANUARY.toString(),
            Month.FEBRUARY.toString(),
            Month.MARCH.toString(),
            Month.APRIL.toString(),
            Month.MAY.toString(),
            Month.JUNE.toString(),
            Month.JULY.toString(),
            Month.AUGUST.toString(),
            Month.SEPTEMBER.toString(),
            Month.OCTOBER.toString(),
            Month.NOVEMBER.toString(),
            Month.DECEMBER.toString()
        )
    )
        private set
    override var listYear = mutableStateOf(getListYears())
        private set
    override var indexMonth = mutableStateOf(0)
        private set
    override var indexYear = mutableStateOf(0)
        private set
    override var dialogTitle = mutableStateOf("List name")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set
    private val _showToast = MutableSharedFlow<Boolean>()
    val showToastMessage = _showToast.asSharedFlow()
    var currentRoute = ""
    private var localDate = LocalDate.now()

    val openDropdownMenu = mutableStateOf(false)
    var listWorkoutFlow: Flow<List<WorkoutListItem>>? = null
    private var itemWorkout: WorkoutListItem? = null
    private var itemFood: CalculationCalorieItem? = null
    private var choiceDialog: String? = null
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    override var cancelSwipe = mutableStateOf(false)
    val listOfCurrentMonth = mutableStateOf<Date>(
        Date(
            localDate.month.name,
            localDate.year,
            test(localDate.lengthOfMonth()),
            selectedOfSetDayOfWeek(localDate)
        )
    )

    fun test(daysInMonth: Int): List<DayTraining>{
        val listR = mutableListOf<DayTraining>()
        for(i in 1..daysInMonth){
            listR.add(
                DayTraining(
                    i,
                    false
                )
            )

        }
        return listR
    }


    val openTitle = mutableStateOf(false)
    val openTitleButton = mutableStateOf(false)
    val calendarScale = mutableStateOf(true)
    val selectedDate = mutableStateOf<CanvasParametr>(CanvasParametr(Offset.Zero, 0.0f, "", 0f))
    val todayDate = mutableStateOf<Date>(getCurrentDateList())
    val rows = mutableStateOf(0)
    val aspectRatio = mutableStateOf(1.3f)


    //FoodList value
    var listFoodFlow: Flow<List<CalculationCalorieItem>>? = null
    val sumFoodParameter = mutableStateOf(
        FoodDate(
            name = "",
            calories = 0.0,
            proteins = 0.0,
            fats = 0.0,
            carbohydrates = 0.0,
            checking = false
        )
    )

    init {
        getDayOfMonthTest(localDate.lengthOfMonth())

//        Log.d("LogCalendar", "init start")
//            // getDayOfMonthTest(localDate.lengthOfMonth())
//        getDayOfMonth(localDate.lengthOfMonth())
//        listOfCurrentMonth.value = Date(
//            localDate.month.name,
//            localDate.year,
//            getDayOfMonth(localDate.lengthOfMonth()),
//            selectedOfSetDayOfWeek(localDate)
//        )
        Log.d("LogCalendar", "init stop")
    }


    fun onEvent(event: CalendarEvent) {
        when (event) {
            is CalendarEvent.ClickDay -> {
                Log.d("LogClickDay", "Click, ${selectedDateToString(event.day, localDate)} / " +
                        "selected date = ")
                if (selectedDateToString(event.day, localDate).equals(selectedDate.value.date)) {
                    Log.d("LogClickDay", "Click day if 1")
                    resetSelectedDay()
                } else {
                    Log.d("LogClickDay", "Click day else 1")
                    selectedDate.value.date = selectedDateToString(event.day, localDate)
                    openTitle.value = true
                    openTitle(selectedDate.value.date)
                    if (listOfCurrentMonth.value.dayInMonth.get(event.day.day - 1).training == true) {
                        Log.d("LogClickDay", "Click training = true")
                        viewModelScope.launch {
                            listWorkoutFlow = getAllItemsByDateFlow(event.day.day.toString())
                        }
                    } else {
                        Log.d("LogClickDay", "Click training = false")
                        viewModelScope.launch {
                            listWorkoutFlow = null
                            listFoodFlow = null
                        }
                    }
                }
                //calendarScale.value = false
            }

            is CalendarEvent.ChangeMonth -> {
                selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "", 0f)
                lastNextMonth(event.change)
                if (listOfCurrentMonth.value.dayOfWeek >= 6) {
                    rows.value = 1
                    aspectRatio.value = 1.1f
                    selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "", 20f)
                } else if (listOfCurrentMonth.value.dayOfWeek >= 5 && listOfCurrentMonth.value.dayInMonth.size >= 31) {
                    rows.value = 1
                    aspectRatio.value = 1.1f
                    selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "", 20f)
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

            is CalendarEvent.AddFood -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }

            is CalendarEvent.ClickWorkout -> {
                val date =
                    if (getCurrentDate().elementAt(0).toString() == "0") getCurrentDate().drop(1)
                    else getCurrentDate()
                if (selectedDate.value.date.trim() == date.trim()
                    || selectedDate.value.date.trim() < date.trim()
                ) {
                    sendUiEvent(UiEvent.Navigate(event.route))
                } else {
                    sendUiEvent(UiEvent.ShowToast(selectedDate.value.date))
                }
            }

            is CalendarEvent.DeleteTrainingInDay -> {
                openDialog.value = true
                dialogTitle.value = "Delete all workout for ${event.date}"
            }

            is CalendarEvent.OpenDialogWorkout -> {
                openDialog.value = true
                addTraining.value = false
                selectedMonthYear.value = false
                itemWorkout = event.workout
                dialogTitle.value = when (event.dialog) {
                    Routes.DIALOG_DELETE_TRAINING -> {
                        choiceDialog = event.dialog
                        "${event.title}  ${event.workout.date}"

                    }

                    Routes.DIALOG_DELETE_WORKOUT -> {
                        Log.d("LogDialogEvent", event.dialog)
                        choiceDialog = event.dialog
                        "${event.title} ${event.workout.workoutName}"

                    }

                    Routes.DIALOG_EDIT -> {
                        Log.d("LogDialogEvent", event.dialog)
                        choiceDialog = event.dialog
                        "${event.title} ${event.workout.workoutName}"
                    }

                    else -> {
                        ""
                    }
                }
            }

            is CalendarEvent.OpenDialogFood -> {
                openDialog.value = true
                addTraining.value = false
                selectedMonthYear.value = false
                itemFood = event.food
                dialogTitle.value = when (event.dialog) {
                    Routes.DIALOG_DELETE_TRAINING -> {
                        choiceDialog = event.dialog
                        "${event.title}  ${event.food.date}"

                    }

                    Routes.DIALOG_DELETE_WORKOUT -> {
                        Log.d("LogDialogEvent", event.dialog)
                        choiceDialog = event.dialog
                        "${event.title} ${event.food.name}"

                    }

                    Routes.DIALOG_EDIT -> {
                        Log.d("LogDialogEvent", event.dialog)
                        choiceDialog = event.dialog
                        "${event.title} ${event.food.name}"
                    }

                    else -> {
                        ""
                    }
                }
            }

            is CalendarEvent.GetTraining -> {
                listWorkoutFlow = getAllItemsByDateFlow(event.date)
            }

            is CalendarEvent.SaveCanvasParametr -> {
                Log.d("LogSaceveCanvas", "save par")
                selectedDate.value = CanvasParametr(
                    offset = event.canvasPar.offset,
                    radios = event.canvasPar.radios,
                    date = selectedDateToString(
                        DayTraining(event.canvasPar.date.toInt()),
                        localDate
                    ),
                    selectedDate.value.positionPic
                )
            }

            is CalendarEvent.SaveCurrentRoute -> {
                currentRoute = event.route
                if (currentRoute == Routes.LIST_WORKOUT) {
                    viewModelScope.launch {
                        listWorkoutFlow = getAllItemsByDateFlow(selectedDate.value.date)
                    }
                    listFoodFlow = null
                } else if (currentRoute == Routes.FOOD_SCREEN) {

                    getFoodList()
                    listWorkoutFlow = null
                    viewModelScope.launch {
                        sumParameterFood()
                    }

                }
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnConfirm -> {
                when (choiceDialog) {
                    Routes.DIALOG_DELETE_WORKOUT -> {


                            if (itemWorkout != null) {
                                runBlocking {
                                    repository.deleteItem(itemWorkout!!)
                                    val listItem: List<WorkoutListItem> =
                                        repository.getAllItemsByDate(selectedDate.value.date)
                                    if (listItem.size == 0) {
                                        deleteTrainingIcon(0)
                                    }
                                    itemWorkout = null
                                }
                            } else if (itemFood != null) {
                                viewModelScope.launch {
                                    repositoryFood.deleteItem(itemFood!!)
                                    itemFood = null
                                    sumParameterFood()
                                }
                            }
                    }

                    Routes.DIALOG_DELETE_TRAINING -> {
                        viewModelScope.launch {
                            val list = repository.getAllItemsByDate(selectedDate.value.date)
                            list.forEach { item ->
                                repository.deleteItem(item)
                            }
                            deleteTrainingIcon(0)
                        }
                    }

                    Routes.DIALOG_EDIT -> {
                        if (itemWorkout != null) {
                            sendUiEvent(
                                UiEvent.Navigate(
                                    Routes.WORKOUT_LIST +
                                            "/${selectedDate.value.date}" +
                                            "/${itemWorkout?.id}"
                                )
                            )
                        } else if (itemFood != null) {
                            sendUiEvent(
                                UiEvent.Navigate(
                                    Routes.FOOD_SCREEN +
                                            "/${selectedDate.value.date}" +
                                            "/${itemFood?.id}"
                                )
                            )
                        }
                    }

                    Routes.DIALOG_MONTH_YEAR -> {
                        //  resetSelectedDay()
                        openTitle.value = false
                        val month = Month.of(indexMonth.value + 1)
                        localDate = localDate.minusMonths(
                            calculationMonthOfDate(
                                listYear.value.get(indexYear.value).toInt(), month
                            )
                        )
                        getDayOfMonthTest(localDate.lengthOfMonth())
                       // getDayOfMonth(localDate.lengthOfMonth())
                        resetSelectedDay()
                        changesCanvasParameter()
                        openDialog.value = false
                    }
                }
                choiceDialog = null
                itemWorkout = null
                openDialog.value = false
            }

            is DialogEvent.choseMonthYear -> {

            }

            is DialogEvent.OnCancel -> {
                cancelSwipe.value = true
                openDialog.value = false
            }

            is DialogEvent.OnReturnCurrentMonth -> {
                if (localDate.month.toString() != todayDate.value.month || localDate.year != todayDate.value.year) {
                    localDate = LocalDate.now()
                    getDayOfMonth(localDate.lengthOfMonth())
                }
                openTitle.value = false
                openDialog.value = false
            }

            else -> {

            }
        }
    }

    fun lastOrNextMonth(event: LastOrNextDateEvent) {
        when (event) {
            is LastOrNextDateEvent.LastTraining -> {
                lastOrNextMonth(true)
            }

            is LastOrNextDateEvent.NextTraining -> {
                lastOrNextMonth(false)
            }
            //text = stringResource(R.string.selected_date) + "\n"
//                        + dialogController.listMonth.value.get(dialogController.indexMonth.value)
//                    .lowercase() + " "
//                        + dialogController.listYear.value.get(dialogController.indexYear.value)
//                    .lowercase(),
            is LastOrNextDateEvent.OpenDropMenu -> {
                indexMonth.value = getIndexMonth() - 1
                indexYear.value = getIndexYear() - 1
                dialogTitle.value =
                    "${event.title} \n ${listMonth.value.get(indexMonth.value).lowercase()}" +
                            " ${listYear.value.get(indexYear.value).lowercase()}"
                openDialog.value = true
                choiceDialog = Routes.DIALOG_MONTH_YEAR
                selectedMonthYear.value = true
            }

            is LastOrNextDateEvent.ClickDate -> {
            }

            is LastOrNextDateEvent.ClickCurrentDate -> {
            }

            is LastOrNextDateEvent.SelectedYearMonth -> {
                val month = Month.valueOf(event.month)
                localDate = localDate.minusMonths(calculationMonthOfDate(event.year, month))
                    //getDayOfMonth(localDate.lengthOfMonth())
                changesCanvasParameter()
                openDropdownMenu.value = false
            }
        }
    }

    private fun calculationMonthOfDate(year: Int, month: Month): Long {
        val year = localDate.year - year
        val month2 = localDate.month.value - month.value
        val monthres = 12 * year + month2
        return monthres.toLong()
    }

    private fun lastOrNextMonth(change: Boolean) {
        lastNextMonth(change)
        changesCanvasParameter()
    }

    private fun changesCanvasParameter() {
        selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "", 0f)
        if (listOfCurrentMonth.value.dayOfWeek >= 6) {
            rows.value = 1
            aspectRatio.value = 1.1f
            selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "", 20f)
        } else if (listOfCurrentMonth.value.dayOfWeek >= 5 && listOfCurrentMonth.value.dayInMonth.size >= 31) {
            rows.value = 1
            aspectRatio.value = 1.1f
            selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "", 20f)
        } else {
            rows.value = 0
            aspectRatio.value = 1.3f
        }
    }

    private fun openTitle(day: String) {
        val formatter = DateTimeFormatter.ofPattern("d.MM.yyyy")
        val day2 = selectedDateToString(DayTraining(LocalDate.now().dayOfMonth), LocalDate.now())
        val currentDay = LocalDate.parse(day, formatter)
        val selectedDay = LocalDate.parse(day2, formatter)
        when {
            selectedDay > currentDay -> {
                openTitleButton.value = false
            }

            selectedDay == currentDay -> {
                openTitleButton.value = true
            }

            selectedDay < currentDay -> {
                openTitleButton.value = true
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
            mutableListOf(DayTraining(localDate.dayOfMonth)),
            selectedOfSetDayOfWeek(localDate),
        )
    }


    private fun getDayOfMonthTest(daysInMonth: Int){
        val listDays = mutableListOf<DayTraining>()
        Log.d("LogCalendar", "start")
        viewModelScope.launch {
            for (i in 1..daysInMonth) {
                Log.d("LogCalendar", "list")
                listDays.add(
                    DayTraining(
                        i,
                        checkTrainingByDate(dateForDB(i, localDate))
                    )
                )
            }
            Log.d("LogCalendar", "set list")
            listOfCurrentMonth.value = Date(
                localDate.month.toString(),
                year = localDate.year,
                listDays,
                selectedOfSetDayOfWeek(localDate),
            )
            changesCanvasParameter()
            Log.d("LogCalendar", listOfCurrentMonth.value.dayInMonth.size.toString())
        }
    }

    private fun getDayOfMonth(daysInMonth: Int): List<DayTraining> {
        val listDays = mutableListOf<DayTraining>()
        Log.d("LogCalendar", "getDayOfMonth start")
        viewModelScope.launch {
            for (i in 1..daysInMonth) {
                listDays.add(
                    DayTraining(
                        i,
                        checkTrainingByDate(dateForDB(i, localDate))
                    )
                )
            }
//            listOfCurrentMonth.value = Date(
//                localDate.month.toString(),
//                year = localDate.year,
//                listDays,
//                selectedOfSetDayOfWeek(localDate),
//            )
           // Log.d("LogCalendar", "end getDayOfMonth")
            Log.d("LogCalendar", listOfCurrentMonth.value.dayInMonth.size.toString())
            changesCanvasParameter()
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
            dt = date
        } else {
            dt = dateForDB(date.toInt(), localDate)
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
        Log.d("LogLastOrNextMonth", localDate.lengthOfMonth().toString())
        getDayOfMonthTest(localDate.lengthOfMonth())
       // getDayOfMonth(localDate.lengthOfMonth())
        resetSelectedDay()
    }

    private fun selectedDateToString(dayTraining: DayTraining, localDate: LocalDate): String {
        return dayTraining.day.toString() + "." + selectedMonth(localDate) + "." + localDate.year.toString()
    }

    private fun dateForDB(day: Int, localDate: LocalDate): String {
        var zero = ""
//        if (day in 1..9) {
//            zero = "0"
//        }
        val date =
            zero + day.toString() + "." + selectedMonth(localDate) + "." + localDate.year.toString()
        return date
    }

    private fun deleteTrainingIcon(listSize: Int) {
        if (listSize == 0) {
            listOfCurrentMonth.value.dayInMonth
                .get(getTwoSymbol() - 1)
                .training = false
        }
    }

    private fun resetSelectedDay() {
        listWorkoutFlow = null
        listFoodFlow = null
        selectedDate.value = CanvasParametr(Offset.Zero, 0.0f, "", 0f)
        openTitle.value = false
    }

    private fun getListYears(): List<String> {
        var date = LocalDate.of(2000, 1, 1)
        val list = mutableListOf<String>()
        for (i in 1..100) {
            val period = Period.ofYears(i)
            list.add(date.plus(period).year.toString())
        }
        return list
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

    private fun getIndexMonth(): Int {
        return localDate.month.value
    }

    private fun getIndexYear(): Int {
        return localDate.year - 2000
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
            Month.SEPTEMBER -> "09"
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

    private suspend fun sumParameterFood() {
        var calories = 0.0
        var proteins = 0.0
        var fats = 0.0
        var carbohydrates = 0.0
        listFoodFlow?.collect { list ->
            if (list.size == 0) {
                sumFoodParameter.value = returnSumFood(0.0, 0.0, 0.0, 0.0)
                Log.d("LogSumFood", "${sumFoodParameter.value.calories} - meth")
            }
            for (i in list.withIndex()) {
                calories += i.value.calories
                proteins += i.value.proteins
                fats += i.value.fats
                carbohydrates += i.value.carbohydrates
            }
            sumFoodParameter.value = returnSumFood(calories, proteins, fats, carbohydrates)
        }
        Log.d("LogSumFood", "End")
    }

    private fun returnSumFood(
        cal: Double,
        prot: Double,
        fat: Double,
        carb: Double
    ): FoodDate {
        return FoodDate(
            name = "",
            calories = String.format(Locale.ENGLISH, "%.2f", cal).toDouble(),
            proteins = String.format(Locale.ENGLISH, "%.2f", prot).toDouble(),
            fats = String.format(Locale.ENGLISH, "%.2f", fat).toDouble(),
            carbohydrates = String.format(Locale.ENGLISH, "%.2f", carb).toDouble(),
            checking = false
        )
    }

    private fun getFoodList() {
        listFoodFlow = repositoryFood.getAllItemByDate(selectedDate.value.date)
    }
}