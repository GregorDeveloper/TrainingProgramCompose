package gregor.developer.trainingprogramcompose.screen.progress_screen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun ProgressScreen() {
    Text(
        text = "Progress",
        modifier = Modifier.fillMaxSize()
            .wrapContentWidth()
            .wrapContentHeight(),
        color = Color.White
    )
}