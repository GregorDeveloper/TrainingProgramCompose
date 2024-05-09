package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NoteScreen


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ.WeightRepsUnivEvent
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.weight_reps_univ.WeightRepsUnivViewModel

@Composable
fun NoteScreen(
    modifier: Modifier,
    viewModel: WeightRepsUnivViewModel,
) {

    //var text by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(3.dp)
    ) {

        TextField(
            value = viewModel.note.value,
            onValueChange = { newText ->
               // text = newText
                viewModel.onEvent(WeightRepsUnivEvent.OnTextChangeNote(newText))

            },
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.White
            ),
            label = {
                Text(
                    text = "Note",
                    style = TextStyle(
                        color = Color.Green
                    )
                )
            }
        )
    }
}