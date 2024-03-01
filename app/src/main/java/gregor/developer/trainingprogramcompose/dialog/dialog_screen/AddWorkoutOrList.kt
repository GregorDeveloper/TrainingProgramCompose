package gregor.developer.trainingprogramcompose.dialog.dialog_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R


@Preview(showBackground = true)
@Composable
fun AddWorkoutOrList() {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Text(text = "Add workout",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Text(text = "Add training list",
                    style = TextStyle(
                        fontSize = 18.sp
                    ))
            }
        }

}