package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.MuscleItem
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListEvent
import gregor.developer.trainingprogramcompose.utils.Routes


@Composable
fun MuscleItem(
    item: MuscleItem,
    onClickItem: (name: String) -> Unit
) {
    val name = stringResource(item.name)
    Card(
        modifier = Modifier

            .padding(2.dp)
            .clickable {
                onClickItem(name)
            },
        backgroundColor = Color.DarkGray

    ) {
        Column(
            horizontalAlignment = Alignment
                .CenterHorizontally,
        ) {
            Image(
                painter = painterResource(item.imageId),
                contentDescription = "Muscle group",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(3.dp)
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(item.name),
                modifier = Modifier.padding(3.dp),
                style = TextStyle(
                    color = Color.Green
                )
            )
        }
    }

}