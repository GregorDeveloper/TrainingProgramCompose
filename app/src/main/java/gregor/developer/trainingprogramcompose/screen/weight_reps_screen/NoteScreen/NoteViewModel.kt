package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NoteScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(

): ViewModel() {

    val note = mutableStateOf("")
}