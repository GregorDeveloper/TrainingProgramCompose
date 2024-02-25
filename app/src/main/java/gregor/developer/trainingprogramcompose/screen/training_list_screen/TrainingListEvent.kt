package gregor.developer.trainingprogramcompose.screen.training_list_screen

import gregor.developer.training_program_compose.data.entity.TrainingNameItem

sealed class TrainingListEvent{
    data class OnShowDeleteDialog(val item: TrainingNameItem): TrainingListEvent()
    data class OnShowEditDialog(val item: TrainingNameItem): TrainingListEvent()
    data class OnItemClick(val route: String): TrainingListEvent()
    object OnItemSave: TrainingListEvent()
}
