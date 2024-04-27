package gregor.developer.trainingprogramcompose.screen.training_list_screen


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import gregor.developer.trainingprogramcompose.data.swipe_to_dismiss.ParametrSwipeItem
import gregor.developer.trainingprogramcompose.screen.swipe_screen.SwipeItem
import gregor.developer.trainingprogramcompose.dialog.MainDialog
import gregor.developer.trainingprogramcompose.utils.UiEvent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrainingListScreen(
    trainingUpdate: Boolean,
    viewModel: TrainingListViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onNavigate: (String) -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when(event){
                Lifecycle.Event.ON_START -> {
                    if(trainingUpdate){
                        onNavigate("Back")
                    }
                }
                else ->{
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
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
            val dismissThreshold = 0.5f
            val currentFraction = remember{ mutableStateOf(0f) }
            val state = rememberDismissState(
                confirmStateChange = {
                    when (it) {
                        DismissValue.DismissedToStart -> {
                            if(currentFraction.value >= dismissThreshold && currentFraction.value < 1.0f) {
                                viewModel.cancelSwipe.value = false
                                viewModel.onEvent(TrainingListEvent.OnShowDeleteDialog(item))
                                true
                            }else false
                        }
                        DismissValue.DismissedToEnd -> {
                            if(currentFraction.value >= dismissThreshold && currentFraction.value < 1.0f) {
                                viewModel.cancelSwipe.value = false
                                viewModel.onEvent(TrainingListEvent.OnShowEditDialog(item))
                                true
                            }else false
                        }
                        DismissValue.Default -> {
                            viewModel.cancelSwipe.value = false
                            true
                        }
                    }
                },
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
                dismissThresholds = {
                    FractionalThreshold(dismissThreshold)
                },
                background = {
                    currentFraction.value = state.progress.fraction
                    val parametersSwipeItem = state.dismissDirection?.let { ParametrSwipeItem(it) }
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
                    UiTrainingListItem(item, viewModel.date ?: " ") { event ->
                        viewModel.onEvent(event)
                    }
                },
            )
            Divider()
        }
    }
    MainDialog(viewModel)
    BackHandler {
        onNavigate("Back")
    }
}
