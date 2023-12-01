package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepository
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsController
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsEvent
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
import gregor.developer.trainingprogramcompose.utils.workoutObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewWeightRepsViewModel @Inject constructor(
    private val repository: WeightRepsWorkoutRepository
) : ViewModel(), DialogWeightRepsController {
    var item = mutableStateOf<WeightRepsWorkoutItem?>(null)

    val items = mutableListOf<WeightRepsWorkoutItem>()

    var _uiStateTest = MutableStateFlow<List<WeightRepsWorkoutItem>>(emptyList())
    val uiStateTest: StateFlow<List<WeightRepsWorkoutItem>> = _uiStateTest.asStateFlow()
    override var openDialog = mutableStateOf(false)
        private set
    override var weight = mutableStateOf("")
        private set
    override var reps = mutableStateOf("")
        private set

    var listId: Int? = null
    var workoutName: String? = null


    init {
        // listId = savedStateHandle.get<Int>("listId")
        // workoutName = savedStateHandle.get<String>("workoutName")
        workoutName = workoutObject.workoutName
        Log.d(
            "MyLog", workoutName + " workout name" +
                    " date " + getCurrentDate()
        )
        getItemCurrentDate()

    }

    fun onEvent(event: NewWeightRepsEvent) {
        when (event) {
            is NewWeightRepsEvent.OnItemSave -> {
                item.value?.weight += "_" + weight.value
                item.value?.reps += "_" + reps.value

                viewModelScope.launch {
                    Log.d("MyLog", weight.value + " " +
                            reps.value)
                    repository.insertItem(
                        WeightRepsWorkoutItem(
                            item.value?.id,
                            workoutName!!,
                            item.value?.weight ?: weight.value,
                            item.value?.reps ?: reps.value,
                            getCurrentDate()
                        )
                    )

                    items.add(
                        WeightRepsWorkoutItem(
                            item.value?.id,
                            workoutName!!,
                            weight.value,
                            reps.value,
                            getCurrentDate()
                        )
                    )
                    _uiStateTest.update {
                        _uiStateTest.value
                            .toMutableList().apply {
                                WeightRepsWorkoutItem(
                                    item.value?.id,
                                    workoutName!!,
                                    weight.value,
                                    reps.value,
                                    getCurrentDate()
                                )
                            }
                    }
                    clearingData()
                    Log.d("MyLog", items.toString() + "Add item")
                    Log.d("MyLog", _uiStateTest.value.toString())
                }
                if (item == null) {
                    getItemCurrentDate()
                }
            }

            is NewWeightRepsEvent.OnShowDialog -> {
                openDialog.value = true
            }

            is NewWeightRepsEvent.OnCancel -> {

            }

            is NewWeightRepsEvent.OnConfirm -> {

            }

            else -> {}
        }
    }

    override fun onDialogEvent(event: DialogWeightRepsEvent) {
        when (event) {
            is DialogWeightRepsEvent.OnCancel -> {
                openDialog.value = false
            }

            is DialogWeightRepsEvent.OnConfirm -> {
                if (weight.value.isNotEmpty() && reps.value.isNotEmpty()) {
                    onEvent(NewWeightRepsEvent.OnItemSave(weight.value, reps.value))
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

    fun getWeightRepsItem(): WeightRepsWorkoutItem{
        return WeightRepsWorkoutItem(
            item.value?.id,
            workoutName!!,
            item.value?.weight ?: weight.value,
            item.value?.reps ?: weight.value,
            getCurrentDate()
        )
    }

    fun parsItem(){
        val listWeight = item.value?.weight?.split("_")
        val listReps = item.value?.reps?.split("_")

        if(listWeight != null){
            for ((index, element) in listWeight!!.withIndex()){

                items.add(WeightRepsWorkoutItem(
                    item.value!!.id,
                    item.value!!.workOutName,
                    listWeight.get(index),
                    listReps!!.get(index),
                    item.value!!.date
                ))
            }
        }

//        _uiStateTest.update {
//            _uiStateTest.value.toMutableList().add(
//
//            )
//        }
        _uiStateTest.value = items
        Log.d("MyLog", items.toString() + "Add item")
        Log.d("MyLog", _uiStateTest.value.toString())
    }

    fun getItemCurrentDate() {
        viewModelScope.launch {
            item.value = workoutName?.let { repository.getCurrentWeightReps(it, getCurrentDate()) }
            Log.d("MyLog", "Weight Reps Item ${item.value?.weight}")
            parsItem()
        }
    }

    fun clearingData() {
        weight.value = ""
        reps.value = ""

    }


}