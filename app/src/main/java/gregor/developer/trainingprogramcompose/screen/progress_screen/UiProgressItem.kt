package gregor.developer.trainingprogramcompose.screen.progress_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
//    var weight =
//        calculatinItem.weight ?:
//        0.0
//    var reps =
//        calculatinItem.reps ?:
//        0
//    var result  =
//        calculatinItem ?:
//        0.0
    val offset = Offset(5.0f, 10.0f)
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(7.dp),
        backgroundColor = Color.DarkGray) {
        Column(modifier = Modifier.fillMaxWidth()){
            Column {
                //Text(text = stringResource(id = R.string.))
                Text(
                    text = stringResource(id = R.string.formula_brzycki),
                    modifier = Modifier.padding(3.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Green
                    )
                )
                Text(
                    text = "${resultItem.brzycki.result} = ${resultItem.brzycki.weight} / (1.0278 - 0.0278 * ${resultItem.brzycki.reps})",
                    modifier = Modifier.padding(3.dp),
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset,
                            blurRadius = 3f
                        ),
                        fontStyle = FontStyle.Italic
                    )
                )

            }
            Column{
                Text(
                    text = stringResource(id =  R.string.epley),
                    modifier = Modifier.padding(3.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Green
                    )
                )
                Text(
                    text = "${resultItem.epley.result} = ${resultItem.epley.weight} * (1 + ${resultItem.epley.reps} / 30)",
                    modifier = Modifier.padding(3.dp),
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset,
                            blurRadius = 3f
                        ),
                        fontStyle = FontStyle.Italic
                    )
                )

            }
            Column {
                Text(
                    text = stringResource(id =  R.string.lender),
                    modifier = Modifier.padding(3.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Green
                    )
                )
                Text(
                    text = "${resultItem.lender.result} = (100 + ${resultItem.lender.weight}) / (101.3 - 2.67123 * ${resultItem.lender.reps}",
                    modifier = Modifier.padding(3.dp),
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 16.sp,
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