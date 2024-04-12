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
    var workoutName: String? = null

    init {
        workoutName = savedStateHandle.get<String>("workoutName")
        Log.d("MyLog", workoutName + " Start screen weight reps")
        // itemsList = listId?.let { repository.getAllItemsById(it) }
    }
}