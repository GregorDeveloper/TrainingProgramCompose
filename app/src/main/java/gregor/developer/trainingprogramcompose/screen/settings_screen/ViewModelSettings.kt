package gregor.developer.trainingprogramcompose.screen.settings_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ViewModelSettings @Inject constructor(

): ViewModel() {
    private val _showToast = MutableSharedFlow<Boolean>()
    val showToastMessage = _showToast

    fun toAT(){
        viewModelScope.launch {
            _showToast.emit(true)
        }
    }
    init {

    }


}