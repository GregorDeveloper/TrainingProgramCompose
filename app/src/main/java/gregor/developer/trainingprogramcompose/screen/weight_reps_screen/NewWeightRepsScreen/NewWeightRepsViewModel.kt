package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepository
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsController
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsEvent
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.UtilWeightReps
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
import gregor.developer.trainingprogramcompose.utils.workoutObject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewWeightRepsViewModel @Inject constructor(
    private val repository: WeightRepsWorkoutRepository
) : ViewModel(), DialogWeightRepsController{
    var item = mutableStateOf<WeightRepsWorkoutItem?>(null)

    val items = mutableStateListOf<WeightRepsWorkoutItem>()
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

    var listId: Int? = null
    var workoutName: String? = null


    init {
        workoutName = workoutObject.workoutName
        getItemCurrentDate()
    }

    fun onEvent(event: NewWeightRepsEvent) {
        when (event) {
            is NewWeightRepsEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (edit.value) {
                        editWeightReps(editIndexItem.value)
                    } else {
                        item.value?.weight += "_" + weight.value
                        item.value?.reps += "_" + reps.value
                    }
                    repository.insertItem(
                        WeightRepsWorkoutItem(
                            item.value?.id,
                            workoutName!!,
                            item.value?.weight ?: weight.value,
                            item.value?.reps ?: reps.value,
                            getCurrentDate()
                        )
                    )
                    if (item.value == null) {
                        Log.d("MyLog", "item.value == null")
                        getItemCurrentDate()
                    } else if (!edit.value) {
                        Log.d("MyLog", "!edit.value")
                        items.add(
                            WeightRepsWorkoutItem(
                                item.value?.id,
                                workoutName!!,
                                weight.value,
                                reps.value,
                                getCurrentDate()
                            )
                        )
                    }
                    else {
                        val deleteItem = items.get(editIndexItem.value).copy(weight = weight.value, reps = reps.value)
                        items.removeAt(editIndexItem.value)
                        if(showEditText.value) {
                            items.add(editIndexItem.value, deleteItem)
                        }
                    }
                    clearingDialog()
                    edit.value = false
                    showEditText.value = true
                    editIndexItem.value = -1
                }
            }
            is NewWeightRepsEvent.OnShowDialog -> {
                openDialog.value = true
            }
            is NewWeightRepsEvent.OnShowEditDialog -> {
                weight.value = items.get(event.index).weight
                reps.value = items.get(event.index).reps
                editIndexItem.value = event.index
                openDialog.value = true
                edit.value = true

            }
            is NewWeightRepsEvent.OnDeleteDialog -> {
                editIndexItem.value = event.index
                edit.value = true
                openDialog.value = true
                showEditText.value = false

            }
            is NewWeightRepsEvent.OnDeleteItem -> {

            }
            else -> {}
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
                    onEvent(NewWeightRepsEvent.OnItemSave)
                    openDialog.value = false
                }else if (!showEditText.value) {
                    onEvent(NewWeightRepsEvent.OnItemSave)
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
                    }else if(listWeight.size == 1){
                        viewModelScope.launch {
                            repository.deleteItem(item.value!!)
                            return@launch
                        }
                    }
                } else {
                    item.value?.weight += if (item.value?.weight == "") element else "_" + element
                    item.value?.reps += if (item.value?.reps == "") listReps!!.get(index) else "_" + listReps!!.get(index)
                }
            }

        }

    }

    fun parsItem() {
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
                        item.value!!.date
                    )
                )
            }
        }
    }

    fun getItemCurrentDate() {
        viewModelScope.launch {
            item.value =
                workoutName?.let { repository.getCurrentWeightReps(it, getCurrentDate()) }
            parsItem()
        }
    }

    fun splitWeight(): List<String>?{
        return  item.value?.weight?.split("_")
    }
    fun splitReps(): List<String>?{
        return item.value?.reps?.split("_")
    }

    fun clearingDialog() {
        weight.value = ""
        reps.value = ""

    }
}