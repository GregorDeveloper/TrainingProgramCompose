package gregor.developer.trainingprogramcompose.screen.title_date

import android.annotation.SuppressLint
import android.support.v4.os.IResultReceiver2.Default
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.Date
import gregor.developer.trainingprogramcompose.screen.calendar_screen.UiDateList
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TitleDate(
    openChangeDate: Boolean,
    openDropMenu: MutableState<Boolean>,
    openItemCurrentDate: Boolean,
    dateItem: MutableState<Date>?,
    list: List<String>,
    currentDate: String,
    selectedDate: String,
    onLastOrNext: (LastOrNextDateEvent) -> Unit
) {
    var expanded by remember {
        mutableStateOf(openDropMenu)
    }
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    scope.launch { lazyListState.scrollToItem(dateItem?.value?.year?.minus(2002) ?: 40) }
    
    var selectedMonth by remember{mutableStateOf( dateItem?.value?.month)}


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = if (openChangeDate) Arrangement.SpaceBetween else Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (openChangeDate) {
            IconButton(onClick = {
                onLastOrNext(LastOrNextDateEvent.LastTraining)
            }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_left
                    ),
                    contentDescription = "left",
                    tint = Color.White,
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                backgroundColor = Color.Transparent,
                modifier = Modifier.clickable {
                    onLastOrNext(LastOrNextDateEvent.OpenDropMenu)
                }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = selectedDate.lowercase(),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .wrapContentSize()
                            .wrapContentSize()
                    )
//                    Text(
//                        text = dateItem?.value?.year.toString(),
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color.Green,
//                        fontSize = 25.sp,
//                        modifier = Modifier
//                            .wrapContentSize()
//                            .wrapContentSize()
//                    )
                }
            }

            DropdownMenu(
                expanded = openDropMenu.value,
                onDismissRequest = { expanded.value = false },
            ) {
                Column(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(10.dp),
                ) {
//                    if (listYears != null) {
//                        LazyRow(state = lazyListState) {
//                            itemsIndexed(listYears){index, item ->
//                                UiDateList(date = item, selectedItem = selectedYear.toString()) { event ->
//                                    onLastOrNext(event)
//                                    selectedYear = item.toInt()
//                                }
//
//                            }
//                        }
//                        Divider(color = Color.Green, thickness = 2.dp)
//                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {

                        if (openItemCurrentDate) {
                            item {
                                Text(
                                    text = currentDate,
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .clickable {
                                            onLastOrNext(
                                                LastOrNextDateEvent.ClickCurrentDate(
                                                    currentDate
                                                )
                                            )

                                        }
                                        .padding(3.dp),
                                )
                            }
                        }
                        itemsIndexed(
                            list
                        ) { index, item ->

                            UiDateList(date = item, selectedItem = selectedMonth.toString()) { event ->
                                onLastOrNext(event)
                                selectedMonth = item
                            }
                        }
                    }
                }
//                if(listYears != null) {
//
//                    Button(onClick = {
//                        if(selectedYear != null && selectedMonth != null){
//                        onLastOrNext(LastOrNextDateEvent.SelectedYearMonth(selectedYear!!, selectedMonth!!))
//
//                    }
//
//                         }) {
//                        Text(text = "OK")
//                    }
//                }


            }

        }



        if (openChangeDate) {
            IconButton(onClick = {
                onLastOrNext(LastOrNextDateEvent.NextTraining)
            }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_right
                    ),
                    contentDescription = "left",
                    tint = Color.White,
                    modifier = Modifier.weight(0.2f)
                )
            }
        }
    }
}