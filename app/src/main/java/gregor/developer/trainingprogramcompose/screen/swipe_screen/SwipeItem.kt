package gregor.developer.trainingprogramcompose.screen.swipe_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gregor.developer.trainingprogramcompose.data.static_data.ParametersSwipeItem


@Composable
fun SwipeItem(parametersSwipeItem: ParametersSwipeItem,
) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            backgroundColor = parametersSwipeItem.color,
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(parametersSwipeItem.icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .align(parametersSwipeItem.positionIcon)
                        .padding(5.dp)
                )
            }
        }

}

