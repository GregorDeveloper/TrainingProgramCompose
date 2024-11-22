package gregor.developer.trainingprogramcompose.dialog.dialog_list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MuscleItemScreen(
    item: MuscleGroupItem
) {
    Column(modifier = Modifier.fillMaxWidth()
        .padding(3.dp)) {
        Text(text = item.name)
        LinearProgressIndicator(
            progress = item.percent / 100,
            color = Color.Green,
            backgroundColor = Color.DarkGray
        )
    }
}