package gregor.developer.trainingprogramcompose.screen.swipe_screen

import androidx.compose.runtime.MutableState

interface SwipeToDismissController {
    val cancelSwipe: MutableState<Boolean>
}