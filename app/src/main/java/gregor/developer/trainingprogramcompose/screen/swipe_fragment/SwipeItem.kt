package gregor.developer.trainingprogramcompose.screen.swipe_fragment

import android.telecom.CallAudioState
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.ParametersSwipeItem



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeItem(parametersSwipeItem: ParametersSwipeItem,
             // state: DismissState
) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            backgroundColor = parametersSwipeItem.color,
//            elevation = animateDpAsState(
//                targetValue =
//                if(state.dismissDirection != null) 4.dp
//                else 0.dp).value
        ) {
//            val scale by animateFloatAsState(
//                targetValue =
//                if(state.targetValue == DismissValue.Default) 0.8f
//                else 1.2f
//            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(parametersSwipeItem.icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .align(parametersSwipeItem.positionIcon)
                        .padding(5.dp)
                       // .scale(scale)
                )
            }

        }

}

