package gregor.developer.trainingprogramcompose.screen.calendar_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.screen.title_date.LastOrNextDateEvent

@Composable
fun UiDateList(
    date: String,
    selectedItem: String,
    clickOldDate: (LastOrNextDateEvent) -> Unit
) {
    Box(modifier = Modifier
        //.fillMaxWidth()
        .clickable {
                clickOldDate(LastOrNextDateEvent.ClickDate(date))
        },
        contentAlignment = Alignment.Center
       ) {
        Text(text = date,
            style = TextStyle(
                color = if(date.equals(selectedItem)) Color.Green else Color.White,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(3.dp)
        )
    }

}