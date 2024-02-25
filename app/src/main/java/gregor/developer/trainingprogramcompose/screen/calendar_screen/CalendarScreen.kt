package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining

@Composable
fun CalendarScreen(
    viewModel: CalendarScreenViewModel = hiltViewModel()
) {
//    val calendarInputList by remember {
//        mutableStateOf(createCalendarList())
//    }
    var clickedCalendarElem by remember {
        mutableStateOf<DayTraining?>(null)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { viewModel.onEvent(CalendarEvent.ChangeMonth(true)) }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_left
                    ),
                    contentDescription = "left",
                    tint = Color.Green,

                    )
            }
            Text(
                text = viewModel.listOfCurrentMonth.value.month,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 40.sp,
            )
            IconButton(onClick = { viewModel.onEvent(CalendarEvent.ChangeMonth(false)) }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_right
                    ),
                    contentDescription = "left",
                    tint = Color.Green,
                    modifier = Modifier.weight(0.2f)
                )
            }
        }
        Calendar(
            dateList = viewModel.listOfCurrentMonth.value,
            onDayClick = { day ->
//                if (clickedCalendarElem != day) {
//                    Log.d("MyLogCalenScreen", clickedCalendarElem.toString())
                    viewModel.onEvent(CalendarEvent.ClickDay(day))
//                    clickedCalendarElem =
//                        viewModel.listOfCurrentMonth.value.dayInMonth.first() { it == day }
//                } else {
//                    clickedCalendarElem = null
//                }
            },
            todayDate = viewModel.todayDate.value,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(viewModel.aspectRatio.value),
            rows = viewModel.rows.value
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
//            val text = if (clickedCalendarElem.toString().equals("null")) "click day"
//            else clickedCalendarElem.toString() + " " +
//            viewModel.listOfCurrentMonth.value.month + " " + viewModel.listOfCurrentMonth.value.year
            val text = viewModel.itemsList?.size.toString()
            Log.d("MyLogCalendarScreen", text)
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

       // UserWorkoutScreen()
        

    }
}