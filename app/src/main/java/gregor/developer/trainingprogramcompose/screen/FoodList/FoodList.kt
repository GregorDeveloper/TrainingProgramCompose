package gregor.developer.trainingprogramcompose.screen.FoodList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.swipe_to_dismiss.ParametrSwipeItem
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarEvent
import gregor.developer.trainingprogramcompose.screen.calendar_screen.CalendarScreenViewModel
import gregor.developer.trainingprogramcompose.screen.swipe_screen.SwipeItem
import gregor.developer.trainingprogramcompose.utils.Routes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodList(
    viewModel: CalendarScreenViewModel,
) {
    val context = LocalContext.current
    val listFood = viewModel.listFoodFlow?.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxWidth()
           // .padding(3.dp)
    ) {
        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(bottom = 60.dp)
        ) {
            if (listFood != null) {
                itemsIndexed(listFood.value,
                    key = { _,
                            listItem ->
                        listItem.hashCode()
                    }) { index, item ->
                    val dismissThreshold = 0.5f

                    val currentFraction = remember { mutableStateOf(0f) }
                    val state = rememberDismissState(
                        confirmStateChange = { dismissValue ->
                            when (dismissValue) {
                                DismissValue.DismissedToStart -> {
                                    if (currentFraction.value >= dismissThreshold && currentFraction.value < 1.0f) {
                                        viewModel.cancelSwipe.value = false
                                    viewModel.onEvent(
                                        CalendarEvent.OpenDialogFood(
                                            item,
                                            Routes.DIALOG_DELETE_WORKOUT,
                                            context.getString(R.string.delete)
                                        )
                                    )
                                        true
                                    } else false
                                }

                                DismissValue.DismissedToEnd -> {
                                    if (currentFraction.value >= dismissThreshold && currentFraction.value < 1.0f) {
                                        viewModel.cancelSwipe.value = false
                                    viewModel.onEvent(
                                        CalendarEvent.OpenDialogFood(
                                            item,
                                            Routes.DIALOG_EDIT,
                                            context.getString(R.string.replace)
                                        )
                                    )
                                        true
                                    } else false
                                }

                                DismissValue.Default -> {
                                    viewModel.cancelSwipe.value = false
                                    true
                                }
                            }
                        }
                    )
                    SwipeToDismiss(
                        state = state,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 3.dp,
                                start = 3.dp,
                                end = 3.dp
                            ),
                        dismissThresholds = {
                            FractionalThreshold(dismissThreshold)
                        },
                        background = {
                            currentFraction.value = state.progress.fraction
                            val parametersSwipeItem =
                                state.dismissDirection?.let {
                                    state.dismissDirection
                                    ParametrSwipeItem(it)
                                }
                            if (parametersSwipeItem != null) {
                                if (viewModel.cancelSwipe.value) {
                                    LaunchedEffect(key1 = viewModel.cancelSwipe.value) {
                                        state.reset()
                                        viewModel.cancelSwipe.value = false
                                    }
                                }
                                SwipeItem(parametersSwipeItem)
                            }
                        },
                        dismissContent = {
                            UiFoodLIstItem(item)
                        },

                        )
                    Divider()
                }
            }
        }
    }
}