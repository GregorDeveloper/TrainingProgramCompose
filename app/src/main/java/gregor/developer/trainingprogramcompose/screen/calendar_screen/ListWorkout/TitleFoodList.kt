package gregor.developer.trainingprogramcompose.screen.calendar_screen.ListWorkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarScreenViewModel

@Composable
fun TitleFoodList(
    sumParameterFood: MutableState<FoodDate>
) {
    val textStyle = TextStyle(
        color = Color.White,
        fontSize = 14.sp
    )
    Row(
        modifier = Modifier
            .fillMaxWidth(),
           // .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.name),
            modifier = Modifier
                .weight(1f)
                .padding(start = 3.dp)
        )
        Text(
            text = stringResource(id = R.string.calories) + ": ${sumParameterFood.value.calories}",
            modifier = Modifier.weight(0.25f),
            style = textStyle,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.protein) + ": ${sumParameterFood.value.proteins}",
            modifier = Modifier.weight(0.25f),
            style = textStyle,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.fat) + ": ${sumParameterFood.value.fats}",
            modifier = Modifier.weight(0.25f),
            style = textStyle,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.carbohydrates) + ": ${sumParameterFood.value.carbohydrates}",
            modifier = Modifier.weight(0.25f),
            style = textStyle,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.25f))
    }
}