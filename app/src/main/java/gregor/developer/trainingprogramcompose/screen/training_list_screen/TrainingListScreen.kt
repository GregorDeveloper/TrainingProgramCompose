package gregor.developer.trainingprogramcompose.screen.training_list_screen


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.data.swipe_to_dismiss.ParameterSwipeItem
import gregor.developer.trainingprogramcompose.screen.swipe_screen.SwipeItem
import gregor.developer.trainingprogramcompose.dialog.MainDialog
import gregor.developer.trainingprogramcompose.screen.progress_indicator.ProgressIndicator
import gregor.developer.trainingprogramcompose.utils.UiEvent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrainingListScreen(
    viewModel: TrainingListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val trainingList = viewModel.list.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }
                else -> {

                }
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        itemsIndexed(
            items = trainingList.value,
            key = { _, listItem ->
                listItem.hashCode()
            }
        ) { index, item ->
            val state = rememberDismissState(
                confirmStateChange = {
                    when (it) {
                        DismissValue.DismissedToStart -> {
                            viewModel.cancelSwipe.value = false
                            viewModel.onEvent(TrainingListEvent.OnShowDeleteDialog(item))
                            true
                        }

                        DismissValue.DismissedToEnd -> {
                            viewModel.cancelSwipe.value = false
                            viewModel.onEvent(TrainingListEvent.OnShowEditDialog(item))
                            true
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
                        top = 18.dp,
                        start = 3.dp,
                        end = 3.dp
                    ),
                background = {
                    val parametersSwipeItem = state.dismissDirection?.let { ParameterSwipeItem(it) }
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
                    UiTrainingListItem(item) { event ->
                        viewModel.onEvent(event)
                    }
                },
            )
            Divider()
        }
    }
    MainDialog(viewModel)
}
