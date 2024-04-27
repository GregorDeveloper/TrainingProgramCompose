package gregor.developer.trainingprogramcompose.data.swipe_to_dismiss

import androidx.compose.material.DismissDirection
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.ParametersSwipeItem

fun ParametrSwipeItem(dismissDirection: DismissDirection): ParametersSwipeItem {

    val swipeDeleteItem = ParametersSwipeItem(
        color = Color.Red,
        icon = R.drawable.delete_icon,
        positionIcon = Alignment.CenterEnd
    )
    val swipeEditItem = ParametersSwipeItem(
        color = Color.Green,
        icon = R.drawable.edit_icon,
        positionIcon = Alignment.CenterStart
    )

    return when (dismissDirection) {
        DismissDirection.StartToEnd -> swipeEditItem
        DismissDirection.EndToStart -> swipeDeleteItem
    }
}
