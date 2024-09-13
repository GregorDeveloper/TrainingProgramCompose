package gregor.developer.trainingprogramcompose.screen.progress_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import gregor.developer.trainingprogramcompose.R

@Composable
fun UiFoodScreen(item: CalculationCalorieItem) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(3.dp),
        backgroundColor = Color.DarkGray
    ) {

        val text = remember {
            mutableStateOf("")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = item.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White
                ),
                modifier = Modifier.weight(1f)
            )

            TextField(
                modifier = Modifier
                    .padding(start = 7.dp)
                    .width(100.dp)
                    .weight(1f),
                value = text.value,
                onValueChange = {newText ->
                    text.value = newText
                },
                label = {
                    Text(
                        text =
                        stringResource(id = R.string.grams)
                    )
                },
                placeholder = { Text(text = item.calories.toString())},
                textStyle = TextStyle(

                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent
                ),
            )

            IconButton(onClick = { /*TODO*/ },
                modifier = Modifier.weight(0.5f)) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.save_icon
                    ),
                    contentDescription = "save or add",
                    tint = Color.White
                )
            }
        }
    }
}