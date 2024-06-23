package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepository
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsController
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsEvent
import gregor.developer.trainingprogramcompose.screen.title_date.LastOrNextDateEvent
import gregor.developer.trainingprogramcompose.utils.UiEvent
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightRepsUnivViewModel @Inject constructor(
    private val repository: WeightRepsWorkoutRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), DialogWeightRepsController {
    private var workoutName: String? = null
    var date = mutableStateOf("")
    var openChangeDate: Boolean = true
    var item = mutableStateOf<WeightRepsWorkoutItem?>(null)
    val items = mutableStateListOf<WeightRepsWorkoutItem>()
    val note = mutableStateOf("")
    var listLastWeightReps = mutableStateOf<List<WeightRepsWorkoutItem>?>(null)
    var listDate = mutableListOf<String>()
    val openDropdownMenu = mutableStateOf(false)
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var openItemCurrentDate = mutableStateOf(true)
    private var index = -1
    override var dialogTitle = mutableStateOf("")
        private set
    override var edit = mutableStateOf(false)
        private set
    override var editIndexItem = mutableStateOf(-1)
        private set
    override var showEditText = mutableStateOf(true)
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var weight = mutableStateOf("")
        private set
    override var reps = mutableStateOf("")
        private set

    init {
        workoutName = savedStateHandle.get<String>("workoutName")
        date.value = savedStateHandle.get<String>("date") ?: ""
        openChangeDate = todayOrLastDate(date.value)
        viewModelScope.launch {
            getItemCurrentDate()
        }

        if (item.value == null) {
            viewModelScope.launch {
                val tr = repository.getWeightReps(workoutName!!)
            }
        }

    }

    fun onEvent(event: WeightRepsUnivEvent) {
        when (event) {
            is WeightRepsUnivEvent.OpenDialog -> {
                dialogTitle.value = event.dialogTitle
                openDialog.value = true
            }

            is WeightRepsUnivEvent.SaveWeightReps -> {
                viewModelScope.launch {
                    getItemCurrentDate()
                    getWeightReps()

                    repository.insertItem(
                        WeightRepsWorkoutItem(
                            item.value?.id,
                            workoutName!!,
                            item.value?.weight ?: weight.value,
                            item.value?.reps ?: reps.value,
                            getCurrentDate(),
                            note.value
                        )
                    )
                    updateUiWeightReps()
                    clearingDialog()
                    edit.value = false
                    showEditText.value = true
                    editIndexItem.value = -1
                }
            }

            is WeightRepsUnivEvent.SaveNote -> {
                if (item.value?.id != null) {
                    viewModelScope.launch {
                        getItemCurrentDate()
                        repository.insertItem(
                            WeightRepsWorkoutItem(
                                item.value?.id,
                                workoutName!!,
                                item.value?.weight ?: "",
                                item.value?.reps ?: "",
                                getCurrentDate(),
                                note.value
                            )
                        )
                    }
                } else {
                    sendUiEvent(UiEvent.ShowToast(getCurrentDate()))
                }
            }

            is WeightRepsUnivEvent.DeleteFullDay -> {
                viewModelScope.launch {
                    repository.deleteItem(item.value!!)
                }
            }

            is WeightRepsUnivEvent.DeleteNumber -> {
                viewModelScope.launch {
                    if (items.size > 1) {
                        deleteWeightReps(index)
                        items.removeAt(index)
                        repository.insertItem(item.value!!)
                    } else {
                        items.removeAt(index)
                        repository.deleteItem(item.value!!)
                    }
                }
            }

            is WeightRepsUnivEvent.OnShowEditDialog -> {
                if (item.value?.date == getCurrentDate()) {
                    weight.value = items.get(event.index).weight
                    reps.value = items.get(event.index).reps
                    editIndexItem.value = event.index
                    dialogTitle.value = event.dialogTitle + (event.index + 1).toString()
                    openDialog.value = true
                    edit.value = true
                } else {

                }

            }

            is WeightRepsUnivEvent.OnTextChangeNote -> {
                note.value = event.note
            }

            is WeightRepsUnivEvent.OpenDeleteDialog -> {
                viewModelScope.launch {
                    if (item.value?.date == getCurrentDate()) {
                        getItemCurrentDate()
                        index = event.index
                        dialogTitle.value = event.dialogTitle + (event.index + 1).toString()
                        openDialog.value = true
                        showEditText.value = false
                    } else {
                        sendUiEvent(UiEvent.ShowToast(getCurrentDate()))
                    }
                }

            }

            is WeightRepsUnivEvent.OpenDeleteFullDayDialog -> {
                viewModelScope.launch {
                    getItemCurrentDate()
                    if (item.value?.id != null) {
                        openDialog.value = true
                        showEditText.value = false
                        dialogTitle.value = event.dialogTitle + item.value?.date
                    } else {
                        openDialog.value = false
                        sendUiEvent(UiEvent.ShowToast(getCurrentDate()))
                    }
                }
            }


            is WeightRepsUnivEvent.OpenDialogDescription -> {
                openDialog.value = true
            }

            WeightRepsUnivEvent.OpenDialogDate -> {
                viewModelScope.launch {
                    listLastWeightReps.value = workoutName?.let { repository.getLastTraining(it) }
                    if (listLastWeightReps.value?.isNotEmpty() == true) {
                        Log.d("LogClickDate", listLastWeightReps.value.toString())
                        for ((index, element) in listLastWeightReps.value?.withIndex()!!) {
                            listDate.add(element.date)
                        }
                        openDropdownMenu.value =
                            if (listLastWeightReps.value?.size!! > 1) true else false
                    }
                }
            }
        }
    }

    fun onLastOrNextEvent(event: LastOrNextDateEvent) {
        viewModelScope.launch {
            val currentId = getCurrentId()
            var currentDateNull = false
            // var result: WeightRepsWorkoutItem? = null
            if (currentId != null) {
                val result = when (event) {
                    is LastOrNextDateEvent.OpenDropMenu -> {
                        listDate.clear()
                        listLastWeightReps.value =
                            workoutName?.let { repository.getLastTraining(it) }
                        if (listLastWeightReps.value?.isNotEmpty() == true) {
                            for ((index, element) in listLastWeightReps.value?.withIndex()!!) {
                                listDate.add(element.date)
                            }
                            openDropdownMenu.value =
                                if (listLastWeightReps.value?.size!! > 1) {
                                    openItemCurrentDate.value=
                                        if (listLastWeightReps.value!!.first().date.equals(getCurrentDate())) false else true
                                    Log.d("LogLastOrNext", openItemCurrentDate.toString())
                                    Log.d(
                                        "LogLastOrNext",
                                        listLastWeightReps.value!!.first().date.toString()
                                    )
                                    true
                                } else {
                                    false
                                }
                        }
                        null
                    }

                    is LastOrNextDateEvent.LastTraining -> {
                        if (item.value?.id == null) {
                            repository.getWeightReps(workoutName!!)
                        } else {
                            workoutName?.let {
                                repository.getLastWeightReps(
                                    it,
                                    currentId
                                )
                            }
                        }
                    }

                    is LastOrNextDateEvent.NextTraining -> {
                        currentDateNull = true
                        if (item.value?.date == getCurrentDate()) return@launch
                        workoutName?.let {
                            repository.getNextTraining(
                                it,
                                currentId
                            )
                        }
                    }

                    is LastOrNextDateEvent.ClickDate -> {
                        openDropdownMenu.value = false
                        workoutName?.let { name ->
                            repository.getWeightRepsByDate(
                                name,
                                event.date
                            )
                        }
                    }

                    is LastOrNextDateEvent.ClickCurrentDate -> {
                        currentDateNull = true
                        openDropdownMenu.value = false
                        workoutName?.let { name ->
                            repository.getWeightRepsByDate(
                                name,
                                event.date
                            )
                        }
                    }

                    is LastOrNextDateEvent.SelectedYearMonth -> {
                        null
                    }
                }
                if (result != null) {
                    item.value = result
                    date.value = result!!.date
                    items.clear()
                    parsItem()
                    return@launch
                } else if (result == null && currentDateNull) {
                    item.value = null
                    clearUiDate()
                }
            } else {
                sendUiEvent(UiEvent.ShowToast(""))
            }
        }
    }

    private suspend fun getCurrentId(): Int? {
        return if (item.value != null) item.value?.id else repository.getWeightReps(workoutName!!)?.id
    }

    private suspend fun getItemCurrentDate() {
        if (item.value?.date != getCurrentDate()) {
            clearUiDate()
            item.value =
                workoutName?.let { repository.getWeightRepsByDate(it, date.value) }
            if (item.value != null) {
                parsItem()
            }
        }
    }

    override fun onDialogEvent(event: DialogWeightRepsEvent) {
        when (event) {
            is DialogWeightRepsEvent.OnCancel -> {
                openDialog.value = false
                showEditText.value = true
                weight.value = ""
                reps.value = ""
            }

            is DialogWeightRepsEvent.OnConfirm -> {
                if (weight.value.isNotEmpty() && reps.value.isNotEmpty()) {
                    onEvent(WeightRepsUnivEvent.SaveWeightReps)
                }
//                else if (!showEditText.value) {
//                    onEvent(WeightRepsUnivEvent.SaveWeightReps)
//
//                }
                else if (index != -1) {
                    onEvent(WeightRepsUnivEvent.DeleteNumber)
                } else {
                    onEvent(WeightRepsUnivEvent.DeleteFullDay)
                }
                openDialog.value = false
            }

            is DialogWeightRepsEvent.OnTextChangeWeight -> {
                weight.value = event.weight
            }

            is DialogWeightRepsEvent.OnTextChangeReps -> {
                reps.value = event.reps
            }

            else -> {}
        }
    }


    private fun editWeightReps(indexE: Int) {
        val listWeight = splitWeight()
        val listReps = splitReps()
        item.value?.weight = ""
        item.value?.reps = ""
        if (listWeight != null) {
            for ((index, element) in listWeight.withIndex()) {
                if (index == indexE) {
                    if (showEditText.value) {
                        item.value?.weight += if (item.value?.weight == "") weight.value else "_" + weight.value
                        item.value?.reps += if (item.value?.reps == "") reps.value else "_" + reps.value
                    } else if (listWeight.size == 1) {
                        viewModelScope.launch {
                            repository.deleteItem(item.value!!)
                            return@launch
                        }
                    }
                } else {
                    item.value?.weight += if (item.value?.weight == "") element else "_" + element
                    item.value?.reps += if (item.value?.reps == "") listReps!!.get(index) else "_" + listReps!!.get(
                        index
                    )
                }
            }

        }
    }

    private fun deleteWeightReps(ind: Int) {
        val listWeight = splitWeight()
        val listReps = splitReps()
        item.value?.weight = ""
        item.value?.reps = ""
        if (listWeight != null) {
            for ((index, element) in listWeight.withIndex()) {
                if (index == ind) {

                } else {
                    item.value?.weight += if (item.value?.weight == "") listWeight.get(index) else "_" + listWeight.get(
                        index
                    )
                    item.value?.reps += if (item.value?.reps == "") listReps?.get(index) else "_" + listReps?.get(
                        index
                    )
                }

            }
        }
    }

    private fun parsItem() {
        val listWeight = splitWeight()
        val listReps = splitReps()
        if (listWeight != null) {
            for ((index, element) in listWeight.withIndex()) {
                items.add(
                    WeightRepsWorkoutItem(
                        item.value!!.id,
                        item.value!!.workOutName,
                        listWeight.get(index),
                        listReps!!.get(index),
                        item.value!!.date,
                        ""
                    )
                )
            }
            note.value = item.value!!.note
        }
    }

    private suspend fun updateUiWeightReps() {
        if (item.value == null) {
            getItemCurrentDate()
        } else if (!edit.value) {
            items.add(
                WeightRepsWorkoutItem(
                    item.value?.id,
                    workoutName!!,
                    weight.value,
                    reps.value,
                    getCurrentDate(),
                    note.value
                )
            )
        } else {
            val deleteItem = items.get(editIndexItem.value)
                .copy(weight = weight.value, reps = reps.value)
            items.removeAt(editIndexItem.value)
            if (showEditText.value) {
                items.add(editIndexItem.value, deleteItem)
            }
        }
    }

    private fun getWeightReps() {
        if (edit.value) {
            editWeightReps(editIndexItem.value)
        } else {
            item.value?.weight += "_" + weight.value
            item.value?.reps += "_" + reps.value
        }
    }

    private fun clearUiDate() {
        date.value = getCurrentDate()
        note.value = ""
        items.clear()
    }

    private fun splitWeight(): List<String>? {
        return item.value?.weight?.split("_")
    }

    private fun splitReps(): List<String>? {
        return item.value?.reps?.split("_")
    }

    private fun clearingDialog() {
        weight.value = ""
        reps.value = ""

    }


    private fun todayOrLastDate(changeDate: String): Boolean {
        var resultDate = changeDate
        if (changeDate.split(".").get(0).toInt() in 1..9) {
            resultDate = "0${changeDate.split(".").get(0)}." +
                    "${changeDate.split(".").get(1)}." +
                    changeDate.split(".").get(2)
        }
        Log.d("LogDateCheck", resultDate)
        Log.d("LogDateCheck", getCurrentDate())
        return if (resultDate.equals(getCurrentDate().trim())) true else false
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}