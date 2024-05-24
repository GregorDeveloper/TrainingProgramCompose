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
import gregor.developer.trainingprogramcompose.utils.Routes
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
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
    var listDate = mutableStateOf<List<WeightRepsWorkoutItem>?>(null)
    val openDropdownMenu = mutableStateOf(false)
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
        getItemCurrentDate()
        if (item.value == null) {
            viewModelScope.launch {
                val tr = repository.getWeightReps(workoutName!!)
            }
        }

    }

    fun onEvent(event: WeightRepsUnivEvent) {
        when (event) {
            is WeightRepsUnivEvent.OpenDialog -> {
                openDialog.value = true
            }

            is WeightRepsUnivEvent.SaveWeightReps -> {
                saveItemCurrentDate()
//                viewModelScope.launch {
//
//                    if (edit.value) {
//                        editWeightReps(editIndexItem.value)
//                    } else {
//                        item.value?.weight += "_" + weight.value
//                        item.value?.reps += "_" + reps.value
//                    }
//                    repository.insertItem(
//                        WeightRepsWorkoutItem(
//                            item.value?.id,
//                            workoutName!!,
//                            item.value?.weight ?: weight.value,
//                            item.value?.reps ?: reps.value,
//                            getCurrentDate(),
//                            item.value?.note ?: ""
//                        )
//                    )
//                    if (item.value == null) {
//                        getItemCurrentDate()
//                    } else if (!edit.value) {
//                        items.add(
//                            WeightRepsWorkoutItem(
//                                item.value?.id,
//                                workoutName!!,
//                                weight.value,
//                                reps.value,
//                                getCurrentDate(),
//                                ""
//                            )
//                        )
//                    } else {
//                        val deleteItem = items.get(editIndexItem.value)
//                            .copy(weight = weight.value, reps = reps.value)
//                        items.removeAt(editIndexItem.value)
//                        if (showEditText.value) {
//                            items.add(editIndexItem.value, deleteItem)
//                        }
//                    }
//                    clearingDialog()
//                    edit.value = false
//                    showEditText.value = true
//                    editIndexItem.value = -1
//                }
            }

            is WeightRepsUnivEvent.OnTextChangeNote -> {
                item.value?.note = event.note

            }

            is WeightRepsUnivEvent.SaveNote -> {
                if (item.value != null) {
                    viewModelScope.launch {
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
                }
            }

            is WeightRepsUnivEvent.OpenDialogDescription -> {

            }

            is WeightRepsUnivEvent.lastNextTraining -> {
            }

            WeightRepsUnivEvent.openDialogDate -> {
                viewModelScope.launch {

                    listDate.value = workoutName?.let { repository.getLastTraining(it) }

                    if(listDate.value?.isNotEmpty() == true){
                        openDropdownMenu.value = if(listDate.value?.size!! > 1) true else false
                    }
                }
            }
        }
    }

    fun onLastOrNextEvent(event: LastOrNextTrainingEvent) {
        viewModelScope.launch {
             val currentId = getCurrentId()
            var currentDateNull = false
            if(currentId != null) {
                val result = when (event) {
                    is LastOrNextTrainingEvent.LastTraining -> {
                        if (item.value == null) {
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

                    is LastOrNextTrainingEvent.NextTraining -> {
                        currentDateNull = true
                        workoutName?.let {
                            repository.getNextTraining(
                                it,
                                currentId
                            )
                        }

                    }

                    is LastOrNextTrainingEvent.ClickDate -> {
                        openDropdownMenu.value = false
                        workoutName?.let { name ->
                            repository.getWeightRepsByDate(
                                name,
                                event.date
                            )
                        }
                    }

                    is LastOrNextTrainingEvent.ClickCurrentDate ->{
                        currentDateNull = true
                        openDropdownMenu.value = false
                        workoutName?.let{name ->
                            repository.getWeightRepsByDate(
                                name,
                                event.date
                            )
                        }
                    }
                }
                Log.d("LogCheckingItemByCurrentDate", result?.date ?: " null")
                if (result != null) {
                    item.value = result
                    date.value = result.date
                    items.clear()
                    parsItem()
                    return@launch
                } else if (result == null && currentDateNull) {
                    date.value = getCurrentDate()
                    note.value = ""
                    items.clear()
                }
            }
        }
    }

    private suspend fun getCurrentId(): Int? {
        return if (item.value != null) item.value?.id else repository.getWeightReps(workoutName!!).id
    }

    private fun saveItemCurrentDate(){
        viewModelScope.launch {
            Log.d("LogSaveItem", item.value?.date ?: " error")
            if(item.value?.date != getCurrentDate()){
                item.value = workoutName?.let { repository.getWeightRepsByDate(it, getCurrentDate()) }
                Log.d("LogSaveItem", item.value?.date ?: " error")
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
                    openDialog.value = false
                } else if (!showEditText.value) {
                    onEvent(WeightRepsUnivEvent.SaveWeightReps)
                    openDialog.value = false
                }
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

    fun getItemCurrentDate() {
        viewModelScope.launch {
            item.value =
                workoutName?.let { repository.getWeightRepsByDate(it, date.value) }
            if (item.value != null) {
                parsItem()
            }
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
        return if (changeDate.trim() == getCurrentDate().trim()) {
            true
        } else {
            false
        }
    }


}