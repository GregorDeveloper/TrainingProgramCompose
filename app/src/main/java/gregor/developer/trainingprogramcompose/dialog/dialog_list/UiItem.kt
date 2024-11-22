package gregor.developer.trainingprogramcompose.dialog.dialog_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UiItem(
    index: Int,
    name: String) {
    Column(modifier = Modifier.padding(2.dp)) {
        Text(text = "$index. $name",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp
            )
        )
    }
}