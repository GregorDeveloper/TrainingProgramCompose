package gregor.developer.trainingprogramcompose.screen.progress_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.calculatinItem
import gregor.developer.trainingprogramcompose.data.static_data.resultItem


@Composable

fun UiProgressItem(
    resultItem: resultItem
) {
    val offset = Offset(5.0f, 10.0f)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .border(1.dp, color = Color.Green, shape = RoundedCornerShape(5.dp)),
        elevation = 5.dp,
        backgroundColor = Color.DarkGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = resultItem.nameWorkout,
                        modifier = Modifier.padding(3.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = resultItem.date,
                        modifier = Modifier.padding(5.dp),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "${resultItem.calculatinItem.result} = ${resultItem.calculatinItem.weight} * (36 / (37 - ${resultItem.calculatinItem.reps}))",
                    modifier = Modifier.padding(3.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset,
                            blurRadius = 3f
                        ),
                        fontStyle = FontStyle.Italic
                    )
                )

            }
        }
    }
}