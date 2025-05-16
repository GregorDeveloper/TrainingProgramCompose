package gregor.developer.trainingprogramcompose.screen.settings_screen


import android.content.Context
import android.graphics.Paint
import android.util.EventLogTags.Description
import android.util.Log
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionAdsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionBackWingArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionBicepsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionCalfArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionCalisthenicsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionCardioArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionChestArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionErectorSpinaeArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionForearmArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionFullBodyArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionHipArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionNeckArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionShouldersArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionTrapeziusArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionTricepsArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionArray.DescriptionYogaArray
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionDialog
import gregor.developer.trainingprogramcompose.screen.ListUniv
import gregor.developer.trainingprogramcompose.screen.food_screen.FoodEvent
import gregor.developer.trainingprogramcompose.screen.food_screen.FoodScreenViewModel
import gregor.developer.trainingprogramcompose.screen.food_screen.ItemList
import gregor.developer.trainingprogramcompose.screen.food_screen.chooseArrayFood
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.annotation.Resource
import kotlin.jvm.internal.Reflection


@Preview(showBackground = true)
@Composable
fun SettingsScreen(
    viewModel: FoodScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val name = context.resources.getStringArray(R.array.yoga_workout)
    val description =  DescriptionYogaArray(context)

    Log.d("LogTestArray", "Array name size = ${name.size}")
    Log.d("LogTestArray", "Array description size =${description.size}")

    for((index, value ) in name.withIndex() ){
        var desc = ""
        if(index <= description.size - 1){
            desc = description.get(index).get(0)
        }
        if(name.get(index).toString().lowercase().equals(desc.lowercase())){

        }else{
           Log.d("LogTestArray", "Array name = ${name.get(index)}")
           Log.d("LogTestArray", "Array description = ${desc}")
        }
    }
}



//@RequiresApi(Build.VERSION_CODES.O)
private fun createCalendarList(): List<CalendarInput> {
    val localDate = LocalDate.now()
    val month = localDate.month
    val year = localDate.year
    val dayOfWeek = LocalDate.of(year, month, 1).lengthOfMonth()
    val d = localDate.dayOfWeek
    val daysInMonth = localDate.lengthOfMonth()
    val month2 = localDate.month.minus(1)

    val localDateMinus = localDate.minusMonths(1)
    val dW = localDateMinus.dayOfWeek
    val dM = localDateMinus.lengthOfMonth()
    val m = localDateMinus.month
    val y = localDateMinus.year
    Log.d("MyLog", dW.toString())
    Log.d("MyLog", dM.toString())
    Log.d("MyLog", m.toString())
    Log.d("MyLog", y.toString())

    val calendarInputs = mutableListOf<CalendarInput>()
    for (i in 1..daysInMonth) {
        calendarInputs.add(
            CalendarInput(
                i,
                toDos = listOf(
                    "Day $i:",
                    "2 p.m. Buying groceries",
                    "4 p.m. Meeting with Larissa"
                )
            )
        )
    }
    return calendarInputs
}


private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLUMNS = 7

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarInput: List<CalendarInput>,
    onDayClick: (Int) -> Unit,
    strokeWidth: Float = 15f,
    month: String
) {

    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }
    var clickAnimationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var animationRadius by remember {
        mutableStateOf(0f)
    }
    val offSet = 2
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = month,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 40.sp
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val column: Int
                            val row: Int
                            val day: Int

                            column =
                                (offset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
                            row = (offset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1
                            day = column + (row - 1) * CALENDAR_COLUMNS - offSet
                            if (day >= 1 && day <= calendarInput.size) {
                                onDayClick(day)
                                clickAnimationOffset = offset
                                scope.launch {
                                    animate(0f, 525f, animationSpec = tween(300)) { value, _ ->
                                        animationRadius = value
                                    }
                                }
                            } else {

                            }

                        }
                    )
                }
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width
            canvasSize = Size(canvasWidth, canvasHeight)
            val ySteps = canvasHeight / CALENDAR_ROWS
            val xSteps = canvasWidth / CALENDAR_COLUMNS

            val column = (clickAnimationOffset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
            val row = (clickAnimationOffset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1

            val path = Path().apply {
                moveTo((column - 1) * xSteps, (row - 1) * ySteps)
                lineTo(column * xSteps, (row - 1) * ySteps)
                lineTo(column * xSteps, row * ySteps)
                lineTo((column - 1) * xSteps, row * ySteps)
                close()
            }

            clipPath(path) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(Color.Green.copy(0.8f), Color.Green.copy(0.2f)),
                        center = clickAnimationOffset,
                        radius = animationRadius + 0.1f
                    ),
                    radius = animationRadius + 0.1f,
                    center = clickAnimationOffset
                )
            }

            drawRoundRect(
                Color.Green,
                cornerRadius = CornerRadius(25f, 25f),
                style = Stroke(
                    width = strokeWidth
                )
            )

            for (i in 1 until CALENDAR_ROWS) {
                drawLine(
                    color = Color.Green,
                    start = Offset(0f, ySteps * i),
                    end = Offset(canvasWidth, ySteps * i),
                    strokeWidth = strokeWidth
                )
            }
            for (i in 1 until CALENDAR_COLUMNS) {
                drawLine(
                    color = Color.Green,
                    start = Offset(xSteps * i, 0f),
                    end = Offset(xSteps * i, canvasHeight),
                    strokeWidth = strokeWidth
                )
            }
            val textHeight = 17.dp.toPx()

            for (i in offSet until calendarInput.size + offSet) {
                val textPositionX: Float
                textPositionX = xSteps * (i % CALENDAR_COLUMNS) + strokeWidth

                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth / 2

                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        calendarInput.get(i - offSet).day.toString(),
                        textPositionX,
                        textPositionY,
                        Paint().apply {
                            textSize = textHeight
                            color = Color.White.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }

}

data class CalendarInput(
    val day: Int,
    val toDos: List<String> = emptyList()
)