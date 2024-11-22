package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout_univ

import gregor.developer.trainingprogramcompose.data.static_data.WorkoutDate

sealed class ListWorkoutEvent {

    data class SaveWorkout(val workout: WorkoutDate): ListWorkoutEvent()
    object SaveList: ListWorkoutEvent()
    object ClearList: ListWorkoutEvent()
    data class AddWorkoutList(val workout: WorkoutDate): ListWorkoutEvent()
}