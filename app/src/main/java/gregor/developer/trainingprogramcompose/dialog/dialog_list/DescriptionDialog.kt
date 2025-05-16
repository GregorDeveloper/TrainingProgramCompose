package gregor.developer.trainingprogramcompose.dialog.dialog_list

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionAdsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionBackWingArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionBicepsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionCalfArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionCalisthenicsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionCardioArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionChestArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionErectorSpinaeArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionForearmArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionFullBodyArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionHipArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionNeckArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionShouldersArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionTrapeziusArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionTricepsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionYogaArray

//@Preview(showBackground = true)
@Composable
fun DescriptionDialog(
    indexCat: Int,
    indexList: Int,
    close: () -> Unit
) {
    val context = LocalContext.current
    val array =  getArraнDescription(indexCat, indexList, context)

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
                    itemList.substringBefore("["),
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
            .padding(7.dp)
            .background(Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = array.get(0),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(painter = painterResource(id = R.drawable.icon_close),
                contentDescription = "close description",
                modifier = Modifier.clickable {
                    close()
                })
        }
        array.forEach {
            Log.d("LogList", it.get(0).toString())
            if (!it.get(0).toString().trim().equals("*")) {
                Text(text = it)
            }
        }
        list.forEach {
            MuscleItemScreen(it)
        }
    }
}



fun getArraнDescription(indexCat: Int, indexL: Int, context: Context): Array<String>{
    val arrayDescription = when(indexCat){
        0 -> {
            DescriptionAdsArray(context)
        }
        1 -> {
            DescriptionBackWingArray(context)
        }
        2 -> {
            DescriptionBicepsArray(context)
        }
        3 -> {
            DescriptionCalfArray(context)
        }
        4 -> {
            DescriptionCalisthenicsArray(context)
        }
        5 -> {
            DescriptionCardioArray(context)
        }
        6 -> {
            DescriptionChestArray(context)
        }
        7 -> {
            DescriptionErectorSpinaeArray(context)
        }
        8 -> {
            DescriptionForearmArray(context)
        }
        9 -> {
            DescriptionFullBodyArray(context)
        }
        10 -> {
            DescriptionHipArray(context)
        }
        11 -> {
            DescriptionNeckArray(context) //LEG!!!
        }
        12 -> {
            DescriptionNeckArray(context)
        }
        13 -> {
            DescriptionShouldersArray(context)
        }
        14 -> {
            DescriptionTrapeziusArray(context)
        }
        15 -> {
            DescriptionTricepsArray(context)
        }
        16 -> {
            DescriptionYogaArray(context)
        }
        else -> {
            DescriptionAdsArray(context)
        }
    }
    return arrayDescription.get(indexL)
}
