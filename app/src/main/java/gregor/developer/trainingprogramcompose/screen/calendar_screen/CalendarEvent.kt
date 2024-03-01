package gregor.developer.trainingprogramcompose.screen.calendar_screen

import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.screen.main_screen.BottomNavItem

sealed class CalendarEvent {
    data class ChangeMonth(val change: Boolean): CalendarEvent()
    data class ClickDay(val day: DayTraining): CalendarEvent()

    data class  ClickWorkout(val workout: WorkoutListItem): CalendarEvent()

    data class AddWorkout(val route: String): CalendarEvent()

    data class AddListWorkout(val route: String): CalendarEvent()

    data class DeleteWorkOut(val workout: WorkoutListItem): CalendarEvent()

    data class DeleteTrainingInDay(val date: String): CalendarEvent()
}
