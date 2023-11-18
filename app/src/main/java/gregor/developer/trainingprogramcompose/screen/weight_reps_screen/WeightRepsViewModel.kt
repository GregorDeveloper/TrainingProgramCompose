package gregor.developer.trainingprogramcompose.screen.weight_reps_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeightRepsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel(){

    var listId: Int? = null

    init {
        listId = savedStateHandle.get<Int>("listId")
       // itemsList = listId?.let { repository.getAllItemsById(it) }
    }
}