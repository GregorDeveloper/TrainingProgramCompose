package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun TitleTableWeightReps() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Number",
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color.Green),
            style = TextStyle(
                color = Color.Green,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center

        )
        Text(
            text = "Weight",
            modifier = Modifier
                .weight(2f)
                .border(1.dp, Color.Green),
            style = TextStyle(
                color = Color.Green,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Reps",
            modifier = Modifier
                .weight(2f)
                .border(1.dp, Color.Green),
            style = TextStyle(
                color = Color.Green,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}