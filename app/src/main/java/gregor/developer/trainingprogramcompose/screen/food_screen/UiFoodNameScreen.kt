package gregor.developer.trainingprogramcompose.screen.food_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UiFoodNameScreen(
    foodDate: FoodDate,
    checking: Boolean,
    clickFood: (FoodDate) -> Unit,
    addList: (FoodDate) -> Unit
) {

    val check = mutableStateOf(foodDate.checking)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .combinedClickable(
                onClick = {
                    if(checking){
                        addList(
                            FoodDate(
                                name = foodDate.name,
                                calories = foodDate.calories,
                                proteins = foodDate.proteins,
                                fats = foodDate.fats,
                                carbohydrates = foodDate.carbohydrates,
                                checking = !check.value
                            ),
                        )
                    check.value = !check.value
                    }else {
                        clickFood(
                            FoodDate(
                                name = foodDate.name,
                                calories = foodDate.calories,
                                proteins = foodDate.proteins,
                                fats = foodDate.fats,
                                carbohydrates = foodDate.carbohydrates,
                                checking = false
                            ),
                        )
                    }

                }
            ),

        colors = CardColors(
            Color.DarkGray,
            Color.DarkGray,
            Color.DarkGray,
            Color.DarkGray,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_food_bank_24),
                contentDescription = "Food name",
                modifier = Modifier.weight(0.5f)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = foodDate.name,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Calories: ${foodDate.calories}",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Proteins: ${foodDate.proteins}",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Fats: ${foodDate.fats}",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Carbohydrates: ${foodDate.carbohydrates}",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(modifier = Modifier.weight(0.2f)) {
                Checkbox(
                    checked = check.value,
                    onCheckedChange = {
                        addList(
                            FoodDate(
                                name = foodDate.name,
                                calories = foodDate.calories,
                                proteins = foodDate.proteins,
                                fats = foodDate.fats,
                                carbohydrates = foodDate.carbohydrates,
                                checking = it
                            ),
                        )
                        check.value = it
                    }
                )
            }

        }
    }
}