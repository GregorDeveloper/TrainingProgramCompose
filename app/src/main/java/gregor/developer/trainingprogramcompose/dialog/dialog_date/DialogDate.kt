package gregor.developer.trainingprogramcompose.dialog.dialog_date

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.DialogController
import gregor.developer.trainingprogramcompose.dialog.DialogEvent
import gregor.developer.trainingprogramcompose.screen.weight_reps_screen.OldWeightRepsScreen.UiDateItem
import gregor.developer.trainingprogramcompose.utils.Routes
import kotlinx.coroutines.launch
import java.time.Month

@SuppressLint("SuspiciousIndentation", "CoroutineCreationDuringComposition")
@Composable
fun DialogDate(dialogController: DialogController) {
    Log.d("LogIndex", dialogController.indexMonth.value.toString())
    Log.d("LogIndex", dialogController.indexYear.value.toString())
    val lazyListStateYear = rememberLazyListState()
    val scopeYear = rememberCoroutineScope()
    scopeYear.launch {
        lazyListStateYear.scrollToItem(
            if (dialogController.indexYear.value > 2) dialogController.indexYear.value - 2
            else dialogController.indexYear.value
        )
    }

    val lazyListStateMonth = rememberLazyListState()
    val scopeMonth = rememberCoroutineScope()
    scopeMonth.launch {
        lazyListStateMonth.scrollToItem(
            if (dialogController.indexMonth.value > 2) dialogController.indexMonth.value - 2
            else dialogController.indexMonth.value
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.fillMaxWidth()
            ) {
            Text(
                text = stringResource(R.string.selected_date) + "\n"
                        + dialogController.listMonth.value.get(dialogController.indexMonth.value)
                    .lowercase() + " "
                        + dialogController.listYear.value.get(dialogController.indexYear.value)
                    .lowercase(),
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            IconButton(onClick = { dialogController.onDialogEvent(DialogEvent.OnReturnCurrentMonth) },
                modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.return_current_month),
                    contentDescription = "return current month",
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier.padding(5.dp)
            ,
            horizontalArrangement = Arrangement.Center,
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                state = lazyListStateMonth,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(
                    dialogController.listMonth.value
                ) { index, item ->
                    UiDateItem(
                        date = item,
                        if (index == dialogController.indexMonth.value) Color.Green else Color.White
                    ) {
                        dialogController.indexMonth.value = index
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f),
                state = lazyListStateYear,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(
                    dialogController.listYear.value
                ) { index, item ->
                    UiDateItem(
                        date = item,
                        if (index == dialogController.indexYear.value) Color.Green else Color.White
                    ) {
                        dialogController.indexYear.value = index
                    }
                }
            }
        }
    }

    //    }
}
//}

