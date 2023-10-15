

package gregor.developer.training_program_compose.weight_reps_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.ui.theme.Grey



@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeightRepsScreen() {
    Scaffold(
        modifier = Modifier
            //.background(Color.Green)
            .fillMaxSize(),
        bottomBar = {

        },
        floatingActionButton = {
            FloatingActionButton(

                onClick = {}
            ) {
                Icon(painter = painterResource(R.drawable.add_icon),
                    contentDescription = "Add",
                    tint = Color.Green
                    )
            }
        }, floatingActionButtonPosition = FabPosition.Center
    ) {

        Text(text = "Test color")
    }
}