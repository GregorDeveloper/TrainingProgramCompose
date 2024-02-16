package gregor.developer.trainingprogramcompose.screen.settings_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.Period
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ViewModelSettings @Inject constructor(

): ViewModel() {
    init {
        calendarNew()
    }

    private fun calendarNew(){

        val calendar: Calendar = Calendar.getInstance()
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val daysList = calendar.get(Calendar.YEAR)
        val d = calendar.get(Calendar.MONTH)


        val localDate = LocalDate.now()
        val month = localDate.month
        val year = localDate.year
        val dayOfWeek = LocalDate.of(year, month, 1).dayOfWeek
        val daysOfMonth = localDate.dayOfMonth
       // val dayOfMonth = LocalDate.of(year, month, 1).



        Log.d("MyLog", dayOfWeek.toString())
        Log.d("MyLog", daysOfMonth.toString())
        Log.d("MyLog", daysInMonth.toString())
     //   Log.d("MyLog", daysList.toString())
      //  Log.d("MyLog", d.toString())

    }
}