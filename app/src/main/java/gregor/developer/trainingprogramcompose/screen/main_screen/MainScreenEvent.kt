package gregor.developer.trainingprogramcompose.screen.main_screen

import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListEvent

sealed class MainScreenEvent{

    object OnShowEditDialog: MainScreenEvent()
    object OnItemSave: MainScreenEvent()
}
