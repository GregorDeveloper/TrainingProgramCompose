package gregor.developer.trainingprogramcompose.dialog.dialog_weight_reps

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import gregor.developer.trainingprogramcompose.dialog.DialogController

@Composable
fun DialogWeightReps(
    dialogController: DialogWeightRepsController
) {
    if(dialogController.openDialog.value){
        Dialog(onDismissRequest = { /*TODO*/ }) {
            
        }
    }
}