package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.Bottom

import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.utils.Routes

sealed class WeightRepsBottomNavItem(
    val title: Int,
    val iconId: Int,
    val route: String
) {

    object NewWeightReps:  WeightRepsBottomNavItem(R.string.NewTraining, R.drawable.new_icon, Routes.NEW_WEIGHT_REPS)
    object OldWeightReps:  WeightRepsBottomNavItem(R.string.OldTraining, R.drawable.old_icon_test, Routes.NEXT_WEIGHT_REPS)
    object NoteWeightReps:  WeightRepsBottomNavItem(R.string.Note, R.drawable.note_icon, Routes.NOTE)
}