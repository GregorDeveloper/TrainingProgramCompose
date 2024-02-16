package gregor.developer.trainingprogramcompose.screen.progress_indicator

import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator() {
  //  var loading by remember { mutableStateOf(false) }

//    Button(onClick = { loading = true }, enabled = !loading) {
//        Text("Start loading")
//    }

    //if (!loading) return

    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = Color.Green,
       // trackColor = Color.DarkGray,
    )
}