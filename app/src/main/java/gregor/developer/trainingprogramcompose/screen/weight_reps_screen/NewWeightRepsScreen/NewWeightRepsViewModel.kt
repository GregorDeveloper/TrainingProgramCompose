package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NewWeightRepsScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsController
import gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps.DialogWeightRepsEvent
import javax.inject.Inject

@HiltViewModel
class NewWeightRepsViewModel @Inject constructor(

): ViewModel(), DialogWeightRepsController {
    override var openDialog = mutableStateOf(false)
        private set
    override var weight = mutableStateOf("")
        private set
    override var reps = mutableStateOf("")
        private set


    fun onEvent(event: NewWeightRepsEvent){
        when(event){
            is NewWeightRepsEvent.OnShowDialog -> {
                openDialog.value = true
            }
            is NewWeightRepsEvent.OnCancel -> {

            }
            is NewWeightRepsEvent.OnConfirm -> {

            }
        }
    }
    override fun onDialogEvent(event: DialogWeightRepsEvent) {
        when(event){
            is DialogWeightRepsEvent.OnCancel -> {

            }
            is DialogWeightRepsEvent.OnConfirm -> {

            }
            is DialogWeightRepsEvent.OnTextChangeWeight ->{

            }
            is DialogWeightRepsEvent.OnTextChangeReps ->{

            }
        }
    }


}