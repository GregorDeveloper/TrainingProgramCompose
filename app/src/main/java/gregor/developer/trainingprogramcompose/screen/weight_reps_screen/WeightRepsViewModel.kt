package gregor.developer.trainingprogramcompose.screen.weight_reps_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.trainingprogramcompose.utils.workoutObject
import javax.inject.Inject

@HiltViewModel
class WeightRepsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel(){
    var listId: Int? = null
    var workoutName: String? = null

    init {
        listId = savedStateHandle.get<Int>("listId")
        workoutName = savedStateHandle.get<String>("workoutName")
        workoutObject.workoutName = workoutName ?: "not"
        Log.d("MyLog", listId.toString() + ","
                + workoutName + " Start screen weight reps")
        // itemsList = listId?.let { repository.getAllItemsById(it) }
    }
}