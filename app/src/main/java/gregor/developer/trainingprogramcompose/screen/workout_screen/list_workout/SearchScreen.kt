package gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun SearchScreen(
    searchWorkout: MutableState<String>,
    onSearchWorkout: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                top = 3.dp,
                start = 5.dp,
                end = 5.dp
            )
    ) {
        TextField(
            value = searchWorkout.value,
            onValueChange = { search ->
                searchWorkout.value = search
                onSearchWorkout(searchWorkout.value)
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = Color.Green, fontSize = 16.sp
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (searchWorkout.value != "") {
                    IconButton(
                        onClick = {
                            searchWorkout.value = ""
                            onSearchWorkout(searchWorkout.value)
                            Log.d("MyLog", searchWorkout.value + " sreachScreen")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }

            }, singleLine = true,
            shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Green,
                cursorColor = Color.Green,
                leadingIconColor = Color.Green,
                trailingIconColor = Color.Green,
                //backgroundColor = colorResource(id = R.color.colorPrimary),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

}
