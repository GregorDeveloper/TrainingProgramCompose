package gregor.developer.trainingprogramcompose.screen.workout_screen.user_workout

import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout.WorkoutEvent

sealed class UserWorkoutEvent{

    object OnSaveList: UserWorkoutEvent()
    data class OnItemClick(val route: String): UserWorkoutEvent()
    data class OnDelete(val item: WorkoutListTraining): UserWorkoutEvent()
    data class OnEditItem(val itemId: Int, val route: String): UserWorkoutEvent()

}
