package gregor.developer.trainingprogramcompose.screen.food_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Index
import gregor.developer.trainingprogramcompose.R


@Composable
fun UiFoodCategoriesScreen(
    name: String,
    color: Color,
    choosingCategory: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(150.dp)
            .padding(5.dp)
            .clickable {
                choosingCategory()
            },
        colors = CardColors(
            Color.DarkGray,
            Color.DarkGray,
            Color.DarkGray,
            Color.DarkGray,
        ),

        ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_food_bank_24),
                contentDescription = "food title"
            )
            Text(
                text = name,
                style = TextStyle(
                    color = color,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }
}