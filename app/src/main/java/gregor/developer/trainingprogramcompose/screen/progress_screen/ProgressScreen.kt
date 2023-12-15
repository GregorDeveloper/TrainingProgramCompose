package gregor.developer.trainingprogramcompose.screen.progress_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.R


@Composable
fun ProgressScreen(
viewModelProgress: ViewModelProgress = hiltViewModel()) {

    val offset = Offset(5.0f, 10.0f)



    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            backgroundColor = Color.DarkGray

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column() {
                    Image(
                        painter = painterResource(id = R.drawable.scheme_test),
                        contentDescription = "muscle scheme"
                    )
                }


                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {

                    Text(
                        text = stringResource(id = R.string.name),
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            shadow = Shadow(
                                color = Color.Black,
                                offset = offset,
                                blurRadius = 3f
                            ),
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.gender),
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            shadow = Shadow(
                                color = Color.Black,
                                offset = offset,
                                blurRadius = 3f
                            ),
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.age),
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            shadow = Shadow(
                                color = Color.Black,
                                offset = offset,
                                blurRadius = 3f
                            ),
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.weight_progress),
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            shadow = Shadow(
                                color = Color.Black,
                                offset = offset,
                                blurRadius = 3f
                            ),
                            fontStyle = FontStyle.Italic
                        )
                    )

                }
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
            ){
            itemsIndexed(viewModelProgress.resultList){index, item ->
                UiProgressItem(
                    item
                )
            }
        }
    }
}