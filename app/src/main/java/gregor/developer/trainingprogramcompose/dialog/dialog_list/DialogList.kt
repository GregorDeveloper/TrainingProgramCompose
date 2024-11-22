package gregor.developer.trainingprogramcompose.dialog.dialog_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import gregor.developer.trainingprogramcompose.R


@Composable
fun DialogList(
    dialogController: DialogListController
) {
    val title = if(dialogController.workoutOrFood.value) stringResource(id = R.string.add_workout)else stringResource(
        id = R.string.add_food
    )
    if(dialogController.openDialog.value){
        AlertDialog(
            onDismissRequest = {
                dialogController.onDialogEvent(DialogListEvent.OnCancel)
            },
            dismissButton = {
                TextButton(onClick = { dialogController.onDialogEvent(DialogListEvent.OnCancel) }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            },
            confirmButton = {
                TextButton(onClick = { dialogController.onDialogEvent(DialogListEvent.OnConfirm)  }) {
                    Text(
                        text = stringResource(id = R.string.save)
                    )
                }},
            title = null,
            modifier = Modifier
                .width(350.dp)
                .height(resultHeight(dialogController.list.size).dp),
            backgroundColor = Color.DarkGray,
            text = {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top) {
                    Text(text = "$title ${dialogController.dateDialog.value}",
                        modifier = Modifier
                        ,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    )
                    LazyColumn(modifier = Modifier.weight(1f)
                        .padding(start = 5.dp)) {
                        itemsIndexed(dialogController.list){index, value ->
                            UiItem(index = index + 1, name = value.name)
                        }
                    }
                }
            },
        )
    }
}

fun resultHeight(index: Int): Int{
    return when(index){
        0 -> 100
        1 -> 125
        2 -> 150
        3 -> 170
        4 -> 200
        5 -> 225
        else -> 250
    }
}