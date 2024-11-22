package gregor.developer.trainingprogramcompose.screen.settings_screen


import android.content.Context
import android.graphics.Paint
import android.util.Log
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.dialog.dialog_list.DescriptionDialog
import gregor.developer.trainingprogramcompose.screen.ListUniv
import gregor.developer.trainingprogramcompose.screen.food_screen.FoodEvent
import gregor.developer.trainingprogramcompose.screen.food_screen.FoodScreenViewModel
import gregor.developer.trainingprogramcompose.screen.food_screen.ItemList
import gregor.developer.trainingprogramcompose.screen.food_screen.chooseArrayFood
import kotlinx.coroutines.launch
import java.time.LocalDate



@Preview(showBackground = true)
@Composable
fun SettingsScreen(
    viewModel: FoodScreenViewModel = hiltViewModel()
) {
    DescriptionDialog()
//    ListUniv(
//        foodOrWorkout = true,
//        search = viewModel.searchFood,
//        chooseArray = {index ->
//            chooseArrayWorkout(index, context)
//        },
//        uiEvent = {
//
//        },
//        fabVisible = viewModel.fabVisible,
//        clearList = {
//            viewModel.onEvent(FoodEvent.ClearList)
//        },
//        saveListAndBack = {
//            viewModel.onEvent(FoodEvent.SaveListAndBack)
//        },
//        checking = viewModel.checkingFood,
//        saveAndBack = {
//
//        },
//        addListFood = {
//
//        }
//    )

}

fun chooseArrayWorkout(index: Int, context: Context): ItemList {
    return when (index) {
        0 -> ItemList(
            context.resources.getStringArray(R.array.abs_workout),
            context.resources.getStringArray(R.array.abs_equipment),
            context.resources.getStringArray(R.array.abs_primary_muscles),
            context.resources.getStringArray(R.array.abs_secondary_muscles),
            arrayOf()
        )

        1 -> ItemList(
            context.resources.getStringArray(R.array.back_wing_workout),
            context.resources.getStringArray(R.array.back_wing_equipment),
            context.resources.getStringArray(R.array.back_wing_primary_muscles),
            context.resources.getStringArray(R.array.back_wing_secondary_muscles),
            arrayOf()
        )

        2 -> ItemList(
            context.resources.getStringArray(R.array.biceps_workout),
            context.resources.getStringArray(R.array.biceps_equipment),
            context.resources.getStringArray(R.array.biceps_primary_muscles),
            context.resources.getStringArray(R.array.biceps_secondary_muscles),
            arrayOf()
        )

        3 -> ItemList(
            context.resources.getStringArray(R.array.calf_workout),
            context.resources.getStringArray(R.array.calf_equipment),
            context.resources.getStringArray(R.array.calf_primary_muscles),
            context.resources.getStringArray(R.array.calf_secondary_muscles),
            arrayOf()
        )

        4 -> ItemList(
            context.resources.getStringArray(R.array.calisthenics_workout),
            context.resources.getStringArray(R.array.calisthenics_equipment),
            context.resources.getStringArray(R.array.calisthenics_primary_muscles),
            context.resources.getStringArray(R.array.calisthenics_secondary_muscles),
            arrayOf()
        )

        5 -> ItemList(
            context.resources.getStringArray(R.array.cardio_workout),
            context.resources.getStringArray(R.array.cardio_equipment),
            context.resources.getStringArray(R.array.cardio_primary_muscles),
            context.resources.getStringArray(R.array.cardio_secondary_muscles),
            arrayOf()
        )

        6 -> ItemList(
            context.resources.getStringArray(R.array.chest_workout),
            context.resources.getStringArray(R.array.chest_equipment),
            context.resources.getStringArray(R.array.chest_primary_muscles),
            context.resources.getStringArray(R.array.chest_secondary_muscles),
            arrayOf()
        )

        7 -> ItemList(
            context.resources.getStringArray(R.array.erector_spinae_workout),
            context.resources.getStringArray(R.array.erector_spinae_equipment),
            context.resources.getStringArray(R.array.erector_spinae_primary_muscles),
            context.resources.getStringArray(R.array.erector_spinae_secondary_muscles),
            arrayOf()
        )

        8 -> ItemList(
            context.resources.getStringArray(R.array.forearm_workout),
            context.resources.getStringArray(R.array.forearm_equipment),
            context.resources.getStringArray(R.array.forearm_primary_muscles),
            context.resources.getStringArray(R.array.forearm_secondary_muscles),
            arrayOf()
        )

        9 -> ItemList(
            context.resources.getStringArray(R.array.full_body_workout),
            context.resources.getStringArray(R.array.full_body_equipment),
            context.resources.getStringArray(R.array.full_body_primary_muscles),
            context.resources.getStringArray(R.array.full_body_secondary_muscles),
            arrayOf()
        )

        10 -> ItemList(
            context.resources.getStringArray(R.array.hip_workout),
            context.resources.getStringArray(R.array.hip_equipment),
            context.resources.getStringArray(R.array.hip_primary_muscles),
            context.resources.getStringArray(R.array.hip_secondary_muscles),
            arrayOf()
        )

        11 -> ItemList(
            context.resources.getStringArray(R.array.leg_workout),
            context.resources.getStringArray(R.array.leg_equipment),
            context.resources.getStringArray(R.array.leg_primary_muscles),
            context.resources.getStringArray(R.array.leg_secondary_muscles),
            arrayOf()
        )

        12 -> ItemList(
            context.resources.getStringArray(R.array.neck_workout),
            context.resources.getStringArray(R.array.neck_equipment),
            context.resources.getStringArray(R.array.neck_primary_muscles),
            context.resources.getStringArray(R.array.neck_secondary_muscles),
            arrayOf()
        )

        13 -> ItemList(
            context.resources.getStringArray(R.array.shoulders_workout),
            context.resources.getStringArray(R.array.shoulders_equipment),
            context.resources.getStringArray(R.array.shoulders_primary_muscles),
            context.resources.getStringArray(R.array.shoulders_secondary_muscles),
            arrayOf()
        )

        14 -> ItemList(
            context.resources.getStringArray(R.array.trapezius_workout),
            context.resources.getStringArray(R.array.trapezius_equipment),
            context.resources.getStringArray(R.array.trapezius_primary_muscles),
            context.resources.getStringArray(R.array.trapezius_secondary_muscles),
            arrayOf()
            )

        15 -> ItemList(
            context.resources.getStringArray(R.array.triceps_workout),
            context.resources.getStringArray(R.array.trapezius_equipment),
            context.resources.getStringArray(R.array.triceps_primary_muscles),
            context.resources.getStringArray(R.array.triceps_secondary_muscles),
            arrayOf()
        )

        16 -> ItemList(
            context.resources.getStringArray(R.array.yoga_workout),
            context.resources.getStringArray(R.array.yoga_equipment),
            context.resources.getStringArray(R.array.yoga_primary_muscles),
            context.resources.getStringArray(R.array.yoga_secondary_muscles),
            arrayOf()
        )

        17 -> ItemList(
            context.resources.getStringArray(R.array.food_array_drinks),
            context.resources.getStringArray(R.array.calories_array_drinks),
            context.resources.getStringArray(R.array.proteins_array_drinks),
            context.resources.getStringArray(R.array.fats_array_drinks),
            context.resources.getStringArray(R.array.carbohydrates_array_drinks),
        )

        else -> ItemList(
            context.resources.getStringArray(R.array.abs_workout),
            context.resources.getStringArray(R.array.abs_equipment),
            context.resources.getStringArray(R.array.abs_primary_muscles),
            context.resources.getStringArray(R.array.abs_secondary_muscles),
            context.resources.getStringArray(R.array.abs_secondary_muscles),
        )
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