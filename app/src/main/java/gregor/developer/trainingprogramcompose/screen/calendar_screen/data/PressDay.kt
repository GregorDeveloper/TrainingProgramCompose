package gregor.developer.trainingprogramcompose.screen.calendar_screen.data

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.launch

@Composable
fun PressDay(offset: Offset, canvasSize: Size) {
//
//        val column: Int
//        val row: Int
//        val day: Int
//        column =
//            (offset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
//        row =
//            (offset.y / canvasSize.height * (CALENDAR_ROWS + rowss.value)).toInt() + 1
//        day = column + (row - 1) * CALENDAR_COLUMNS - dayOfWeek.value
//        if (day >= 1 && day <= daysOfMonth.value.size) {
//            onDayClick(daysOfMonth.value.get(day - 1))
//            if (day != clickDay.value) {
//                clickDay.value = day
//                clickAnimationOffset = offset
//                scope.launch {
//                    animate(0f, 525f, animationSpec = tween(300)) { value, _ ->
//                        animationRadius = value
//                    }
//                }
//            }else if(day == clickDay.value) {
//                animationRadius = 0f
//                clickDay.value = -1
//            }
//        }

}