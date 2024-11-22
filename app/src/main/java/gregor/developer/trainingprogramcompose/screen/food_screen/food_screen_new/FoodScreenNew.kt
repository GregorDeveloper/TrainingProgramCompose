package gregor.developer.trainingprogramcompose.screen.food_screen.food_screen_new

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DialogList
import gregor.developer.trainingprogramcompose.screen.ListUniv
import gregor.developer.trainingprogramcompose.screen.food_screen.FoodEvent
import gregor.developer.trainingprogramcompose.utils.UiEvent

@Composable
fun FoodScreenNew(
    viewModel: FoodScreenNewViewModel = hiltViewModel(),
    onBack:(Boolean) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ uiEvent ->
            when(uiEvent){
                is UiEvent.BackStack -> {
                    onBack(true)
                }
                else -> {

                }
            }

        }
    }

    ListUniv(
        foodOrWorkout = false,
        search = viewModel.search,
        fabVisible = viewModel.fabVisibility,
        id = viewModel.listId ?: -1,
        clickDescription = {},
        clearList = { viewModel.onEvent(FoodEventNew.ClearList) },
        saveListAndBack = { viewModel.onEvent(FoodEventNew.SaveListAndBack) },
        checking = viewModel.list,
        saveAndBack = { viewModel.onEvent(FoodEventNew.SaveAndBack(it))},
        addListFood = { food ->
            viewModel.onEvent(FoodEventNew.AddListFood(food))
        }
    )
    DialogList(dialogController = viewModel)
}