package gregor.developer.trainingprogramcompose.dialog.dialog_list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.progress_indicator.ProgressIndicator

@Preview(showBackground = true)
@Composable
fun DescriptionDialog() {

    val context = LocalContext.current
    val array = context.resources.getStringArray(R.array.descriptionOneArmMedicineBallSlamabs)
    var text = ""
    val list = mutableListOf<MuscleGroupItem>()
    var r = ""
    for (i in 0..array.size - 1) {
        if (array.get(i).get(0).toString().trim().equals("*")) {
            val itemList = array.get(i).replace("**", "")
            val length = array.get(i).substringAfter("[")
            r = "${length.substringBefore("]")}"
            list.add(
                MuscleGroupItem(
                    itemList,
                    r.toFloat()
                )
            )
        } else {
            text = "$text ${array.get(i)} \n"
        }
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        array.forEach {
            Log.d("LogList", it.get(0).toString())
            if(!it.get(0).toString().trim().equals("*")){
                Text(text = it)
            }
        }
        list.forEach{
            MuscleItemScreen(it)
        }
    }
}
