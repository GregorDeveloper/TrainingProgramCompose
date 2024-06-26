package gregor.developer.trainingprogramcompose.utils

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun ViewModel.getCurrentDate(): String{
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val cv = Calendar.getInstance()
    return formatter.format(cv.time)
}