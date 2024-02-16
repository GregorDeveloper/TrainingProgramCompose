package gregor.developer.trainingprogramcompose.screen.progress_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.screen.progress_indicator.ProgressIndicator
import gregor.developer.trainingprogramcompose.screen.training_list_screen.TrainingListEvent


@Composable
fun ProgressScreen(
    viewModelProgress: ViewModelProgress = hiltViewModel(),
) {

    val offset = Offset(5.0f, 10.0f)
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            //elevation = 5.dp,
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
                        text = stringResource(id = R.string.name) + " Gregor",
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
                        text = stringResource(id = R.string.gender) + " Male",
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
                        text = stringResource(id = R.string.age) + " 29",
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
                        text = stringResource(id = R.string.weight_progress) + " 88",
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            backgroundColor = Color.DarkGray
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.calculation_max_weight),
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.weight(0.8f)
                    )
                    IconButton(onClick = {
                        viewModelProgress.onEvent(ProgressEvent.openRM)
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (viewModelProgress.openCalculationRM.value) R.drawable.arrow_up_icon
                                else R.drawable.arrow_down_icon
                            ),
                            tint = Color.Green,
                            contentDescription = "open"
                        )
                    }

                }
                if (viewModelProgress.openCalculationRM.value) {
                    Text(
                        text = stringResource(id = R.string.formula_brzycki) + "\n"
                                + stringResource(id = R.string.formula),
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                        )
                    Text(
                        text = stringResource(id = R.string.RM) + "\n"
                                + stringResource(id = R.string.M) + "\n"
                                + stringResource(id = R.string.r)
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(bottom = 100.dp)
                    ) {
                        itemsIndexed(viewModelProgress.resultList) { index, item ->
                            UiProgressItem(
                                item
                            )
                        }
                    }
                }
            }
        }

    }
}