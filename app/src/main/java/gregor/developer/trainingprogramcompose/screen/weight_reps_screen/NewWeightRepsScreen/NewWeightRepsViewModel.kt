package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsController
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsEvent
import javax.inject.Inject

@HiltViewModel
class NewWeightRepsViewModel @Inject constructor(

): ViewModel(), DialogWeightRepsController {
    var item: WeightRepsWorkoutItem? = null
    override var openDialog = mutableStateOf(false)
        private set
    override var weight = mutableStateOf("")
        private set
    override var reps = mutableStateOf("")
        private set

    var listId: Int? = null
    var workoutName: String? = null
    //  val currentDate = getCurrentTime()


    init {
        // listId = savedStateHandle.get<Int>("listId")
        // workoutName = savedStateHandle.get<String>("workoutName")
        Log.d(
            "MyLog", workoutName + " workout name"
        )
//        viewModelScope.launch {
//            item = workoutName?.let { repository.getCurrentWeightReps(it, currentDate) }
//            Log.d("MyLog", "Weight Reps Item ${item?.workOutName}")
//        }
    }

    fun onEvent(event: NewWeightRepsEvent) {
        when (event) {

            is NewWeightRepsEvent.OnItemSave -> {

//                viewModelScope.launch {
//                    repository.insertItem(WeightRepsWorkoutItem(
//                        item?.id,
//                        workoutName!!,
//                        weight.value,
//                        reps.value,
//                        currentDate
//                    ))
//                }
                Log.d(
                    "MyLog",
                    "weight = ${weight.value};" +
                            " reps = ${reps.value}; " +
                            "date = {currentDate}" +
                            "workoutName = ${workoutName}"
                )
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
                    clearingData()
                }
            }

            is DialogWeightRepsEvent.OnTextChangeWeight -> {
                weight.value = event.weight
            }

            is DialogWeightRepsEvent.OnTextChangeReps -> {
                reps.value = event.reps
            }
        }
    }

    fun clearingData() {
        weight.value = ""
        reps.value = ""

    }


}