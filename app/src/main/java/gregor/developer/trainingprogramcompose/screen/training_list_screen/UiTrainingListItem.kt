package gregor.developer.trainingprogramcompose.screen.training_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.utils.Routes

@Composable
fun UiTrainingListItem(
    item: TrainingNameItem,
    onEvent: (TrainingListEvent) -> Unit
) {

    ConstraintLayout(
        modifier = Modifier.padding(
//            top = 18.dp,
//            start = 3.dp,
//            end = 3.dp
        )
    ) {
        val (card, editButton, deleteButton) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable {
                    onEvent(
                        TrainingListEvent.OnItemClick(
                            Routes.USER_WORKOUT_LIST + "/${item.id}"
                        )
                    )
                },
            backgroundColor = Color.DarkGray
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = item.numberOfList.toString(),
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 12.sp
                    )
                )
            }
        }
        IconButton(
            onClick = {
                onEvent(TrainingListEvent.OnShowDeleteDialog(item))
            },
            modifier = Modifier.constrainAs(deleteButton) {
                top.linkTo(card.top)
                bottom.linkTo(card.top)
                end.linkTo(card.end)
            }.padding(end = 10.dp).size(30.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.delete_icon),
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Red).padding(5.dp),
                tint = Color.White
            )
        }
        IconButton(
            onClick = {
                onEvent(TrainingListEvent.OnShowEditDialog(item))
            },
            modifier = Modifier.constrainAs(editButton) {
                top.linkTo(card.top)
                bottom.linkTo(card.top)
                end.linkTo(deleteButton.start)
            }.padding(end = 5.dp).size(30.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.edit_icon),
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Green).padding(5.dp),
                tint = Color.White
            )
        }
    }
}