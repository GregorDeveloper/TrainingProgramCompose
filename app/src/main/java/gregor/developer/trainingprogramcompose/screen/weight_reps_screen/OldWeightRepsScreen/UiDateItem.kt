package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.OldWeightRepsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListEvent


@Composable
fun UiDateItem(
    date: String,
    color: Color,
    onEvent: (String) -> Unit
               ) {
    Box(modifier = Modifier.fillMaxHeight()
        .clickable {
        onEvent(date)
    },
        ){
        Text(text = date,
            style = TextStyle(
                color = color,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
        )
    }
}