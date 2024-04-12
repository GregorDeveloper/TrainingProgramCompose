package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.OldWeightRepsScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepository
import gregor.developer.trainingprogramcompose.dialog.dialog_date.DialogDateController
import gregor.developer.trainingprogramcompose.dialog.dialog_date.DialogDateEvent
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
import gregor.developer.trainingprogramcompose.utils.workoutObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OldWeightRepsViewModel @Inject constructor(
    private val repository: WeightRepsWorkoutRepository
) : ViewModel(), DialogDateController {

    override var openDialog = mutableStateOf(false)
        private set

    override var listDate = mutableListOf("")
        private set


    var workoutName: String? = null
    private var list = listOf<WeightRepsWorkoutItem?>(null)
    val itemsList = mutableStateListOf<WeightRepsWorkoutItem?>()
    val date = mutableStateOf("")
    var number: Int = -1
    init {
        workoutName = workoutObject.workoutName
        if(number != -1) getList()
    }

    fun onEvent(event: OldWeightRepsEvent){
        when (event){
            is OldWeightRepsEvent.nextItemList -> {
                if (number < list.size - 1) {
                    number++
                    splitList()
                }
            }
            is OldWeightRepsEvent.previousItemList -> {
                if (number > 0) {
                    number--
                    splitList()
                }
            }
            is OldWeightRepsEvent.openDialogDate -> {
                openDialog.value = true
            }
        }
    }

    private fun splitList() {
        date.value = list.get(number)!!.date
        if(date.value == getCurrentDate()){
            number--
        }
        val item = list.get(number)
        val weight = splitWeight(item)
        val reps = splitReps(item)
        date.value = item!!.date
        itemsList.clear()

        for ((index, element) in weight!!.withIndex()) {

            itemsList.add(
                WeightRepsWorkoutItem(
                    id = item.id,
                    workOutName = item.workOutName,
                    weight = element,
                    reps = reps!!.get(index),
                    date = item.date
                )
            )
        }
    }

    private fun getList() {
        viewModelScope.launch {
            list = repository.getAllItemsCurrentTime(workoutName!!)
            Log.d("MyLog", workoutName!!)
            for((index, element) in list.withIndex()){
                Log.d("MyLog", element.toString() + " list rep")
            }
            if(list != null){
                number = list.size - 1
                splitList()
                for ((index, element) in list.withIndex()){
                    if(element!!.date != getCurrentDate()){
                        listDate.add(element.date)
                    }
                }
            }
        }
    }


    fun splitWeight(item: WeightRepsWorkoutItem?): List<String>? {
        return item?.weight?.split("_")
    }

    fun splitReps(item: WeightRepsWorkoutItem?): List<String>? {
        return item?.reps?.split("_")
    }


    override fun onDialogDateEvent(event: DialogDateEvent) {
        when(event){
            is DialogDateEvent.OnCancel -> {
                openDialog.value = false
            }
            is DialogDateEvent.OnClickItem -> {
                Log.d("MyLog", event.index.toString() + " index of list")
                number = event.index
                splitList()
                openDialog.value = false

            }
        }
    }
}