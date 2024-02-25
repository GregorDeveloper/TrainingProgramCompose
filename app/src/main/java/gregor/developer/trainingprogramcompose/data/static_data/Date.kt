package gregor.developer.trainingprogramcompose.data.static_data

import java.time.DayOfWeek
import java.time.Month
import java.time.Year

data class Date(
    val month: String,
    val year: Int,
    val dayInMonth: List<DayTraining>,
    val dayOfWeek: Int
)
