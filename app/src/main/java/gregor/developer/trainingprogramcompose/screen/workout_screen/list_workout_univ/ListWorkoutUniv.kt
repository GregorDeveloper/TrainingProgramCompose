package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout_univ

import android.util.Log
import androidx.annotation.ArrayRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionDialog
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DialogList
import gregor.developer.trainingprogramcompose.screen.ListUniv
import gregor.developer.trainingprogramcompose.utils.UiEvent

@Composable
fun ListWorkoutUniv(
    viewModel: ViewModelListWorkout = hiltViewModel(),
    onBack: (Boolean) -> Unit
) {
    val openDescription = remember {
        mutableStateOf(false)
    }
    val arrayName = remember {
        mutableStateOf("")
    }
    val indexCategory = remember {
        mutableStateOf(-1)
    }
    val indexList = remember {
        mutableStateOf(-1)
    }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.BackStack -> {
                    onBack(true)
                }

                is UiEvent.ShowDialog -> {

                }

                else -> {

                }
            }

        }
    }
    ConstraintLayout {
        ListUniv(
            foodOrWorkout = true,
            search = viewModel.search,
            fabVisible = viewModel.fabVisibility,
            id = viewModel.listId ?: -1,
            clickDescription = { name, indexCat, indexL ->
                openDescription.value = true
                arrayName.value = getArrayDescription(name)
                indexCategory.value = indexCat
                indexList.value = indexL
            },
            clearList = { viewModel.onEvent(ListWorkoutEvent.ClearList) },
            saveListAndBack = { viewModel.onEvent(ListWorkoutEvent.SaveList) },
            checking = viewModel.list,
            saveAndBack = {food ->
                viewModel.onEvent(ListWorkoutEvent.SaveWorkout(food))
            },
            addListFood = { workout ->
                viewModel.onEvent(ListWorkoutEvent.AddWorkoutList(workout))
            }
        )

        if(openDescription.value){
            DescriptionDialog(
                indexCategory.value,
                 indexList.value
            ) {
                openDescription.value = !openDescription.value
            }
        }
        DialogList(dialogController = viewModel)

    }

}

private fun getArrayDescription(name: String): String {
    val result = "description${name.replace(" ", "").trim()}ads"
    return result
}