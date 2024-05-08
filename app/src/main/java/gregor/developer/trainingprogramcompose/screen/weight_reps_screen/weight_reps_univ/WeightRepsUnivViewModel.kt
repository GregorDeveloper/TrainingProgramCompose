package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepository
import gregor.developer.trainingprogramcompose.data.repository.NoteRepository
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsController
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsEvent
import gregor.developer.trainingprogramcompose.utils.getCurrentDate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightRepsUnivViewModel @Inject constructor(
    private val repositoryWeightReps: WeightRepsWorkoutRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel(), DialogWeightRepsController {
    private var workoutName: String? = null

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
    init {
        workoutName = savedStateHandle.get<String>("workoutName")
    }

    fun onEvent(event: WeightRepsUnivEvent){
        when(event){
            is WeightRepsUnivEvent.OpenDialog -> {
                openDialog.value = true
            }

            is WeightRepsUnivEvent.SaveWeightReps -> {

            }

            is WeightRepsUnivEvent.DeleteNote -> {

            }

            is WeightRepsUnivEvent.SaveNote -> {

            }

            is WeightRepsUnivEvent.OpenDialogDescription -> {

            }

            WeightRepsUnivEvent.nextItemList -> TODO()

            WeightRepsUnivEvent.openDialogDate -> TODO()

            WeightRepsUnivEvent.previousItemList -> TODO()

        }
    }

    override fun onDialogEvent(event: DialogWeightRepsEvent) {

        when(event){
            DialogWeightRepsEvent.OnCancel -> TODO()
            DialogWeightRepsEvent.OnConfirm -> TODO()
            is DialogWeightRepsEvent.OnTextChangeReps -> TODO()
            is DialogWeightRepsEvent.OnTextChangeWeight -> TODO()
        }
    }

    fun getItemCurrentDate() {
        viewModelScope.launch {
            item.value =
                workoutName?.let { repositoryWeightReps.getCurrentWeightReps(it, getCurrentDate()) }
            parsItem()
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
                            repositoryWeightReps.deleteItem(item.value!!)
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
                        item.value!!.date,
                        ""
                    )
                )
            }
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