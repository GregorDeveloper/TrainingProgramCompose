package gregor.developer.trainingprogramcompose.screen.title_date

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarEvent


@Composable
fun TitleDate(
    title: String,
    modifier: Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            //viewModel.onEvent(CalendarEvent.ChangeMonth(true))
        }) {
            Icon(
                painter = painterResource(
                    id = R.drawable.arrow_left
                ),
                contentDescription = "left",
                tint = Color.Green,
            )
        }
        Text(
            text = "25.03.2024",
            //viewModel.listOfCurrentMonth.value.month,
            fontWeight = FontWeight.SemiBold,
            color = Color.Green,
            fontSize = 25.sp,
            modifier = Modifier.wrapContentSize()
        )
        IconButton(onClick = {
            //viewModel.onEvent(CalendarEvent.ChangeMonth(false))
        }) {
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
}