package gregor.developer.trainingprogramcompose.screen.workout_screen

import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutItem

sealed class WorkoutEvent{
    data class OnDelete(val item: WorkoutListItem): WorkoutEvent()
    data class OnShowDescriptionDialog(val item: WorkoutItem): WorkoutEvent()
    data class OnItemEdit(val item: WorkoutListItem): WorkoutEvent()
    data class OnItemSave(val item: WorkoutItem): WorkoutEvent() // item???
    data class OnItemClick(val route: String): WorkoutEvent()
    data class OnSearchWorkout(val workout: String): WorkoutEvent()
}
