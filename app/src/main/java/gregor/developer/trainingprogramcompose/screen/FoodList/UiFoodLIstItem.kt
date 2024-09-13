package gregor.developer.trainingprogramcompose.screen.FoodList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem


@Composable
fun UiFoodLIstItem(item: CalculationCalorieItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        backgroundColor = Color.DarkGray
    ) {
        var check by remember {
            mutableStateOf(false)
        }
        val textStyle = TextStyle(
            color = Color.White,
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.name,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),

                )
            Text(
                text = item.calories.toString(),
                modifier = Modifier
                    .padding(3.dp)
                    .weight(0.25f),
                style = textStyle,
                textAlign = TextAlign.Center
            )
            Text(
                text = item.proteins.toString(),
                modifier = Modifier
                    .padding(3.dp)
                    .weight(0.25f),
                style = textStyle,
                textAlign = TextAlign.Center
            )
            Text(
                text = item.fats.toString(),
                modifier = Modifier
                    .padding(3.dp)
                    .weight(0.25f),
                style = textStyle,
                textAlign = TextAlign.Center
            )
            Text(
                text = item.carbohydrates.toString(),
                modifier = Modifier
                    .padding(3.dp)
                    .weight(0.25f),
                style = textStyle,
                textAlign = TextAlign.Center
            )
            Checkbox(
                checked = check,
                onCheckedChange = {
                    check = !check
                },
                modifier = Modifier.weight(0.25f),

                )
        }
    }
}