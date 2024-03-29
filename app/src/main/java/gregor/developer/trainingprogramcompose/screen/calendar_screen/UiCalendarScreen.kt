package gregor.developer.trainingprogramcompose.screen.calendar_screen

import android.graphics.Paint
import android.util.Log
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.Date
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.screen.calendar_screen.data.CanvasPar

import kotlinx.coroutines.launch

private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLUMNS = 7

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    dateList: Date,
    onDayClick: (DayTraining) -> Unit,
    strokeWidth: Float = 7f,
    todayDate: Date,
    rows: Int,
    canvasPar: CanvasPar,
    trainingUpdate: Boolean,
    saveClickPar: (CanvasPar) -> Unit,
) {
//передать boolean и поставить картинку если true
    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }
    var clickAnimationOffset by remember {
        mutableStateOf(canvasPar.offset)
    }
    var animationRadius by remember {
        mutableStateOf(canvasPar.radios)
    }
    val daysOfMonth = remember { mutableStateOf(dateList.dayInMonth) }
    val dayOfWeek = remember { mutableStateOf(0) }
    val month = remember { mutableStateOf(dateList.month) }
    dayOfWeek.value = dateList.dayOfWeek
    daysOfMonth.value = dateList.dayInMonth

    val scope = rememberCoroutineScope()
    val clickDay = remember { mutableStateOf(-1) }
    val rowss = remember { mutableStateOf(0) }
    rowss.value = rows
//    if (month.value != dateList.month) {
//        animationRadius = 0f
//        clickDay.value = -1
//    }
    val painter = painterResource(R.drawable.training_list)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
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
                            row =
                                (offset.y / canvasSize.height * (CALENDAR_ROWS + rowss.value)).toInt() + 1
                            day = column + (row - 1) * CALENDAR_COLUMNS - dayOfWeek.value
                            if (day >= 1 && day <= daysOfMonth.value.size) {
                                onDayClick(daysOfMonth.value.get(day - 1))
                                scope.launch {
                                    if (day != clickDay.value) {
                                        clickDay.value = day
                                        clickAnimationOffset = offset
                                        animate(
                                            0f,
                                            525f,
                                            animationSpec = tween(100)
                                        ) { value, _ ->
                                            animationRadius = value
                                        }
                                        saveClickPar(
                                            CanvasPar(
                                                clickAnimationOffset,
                                                animationRadius,
                                                day.toString()
                                            )
                                        )
                                    } else if (day == clickDay.value) {
                                        animationRadius = 0.0f
                                        clickDay.value = -1
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width
            canvasSize = Size(canvasWidth, canvasHeight)
            val ySteps = canvasHeight / (CALENDAR_ROWS + rows)
            val xSteps = canvasWidth / CALENDAR_COLUMNS

            val column = (clickAnimationOffset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
            val row =
                (clickAnimationOffset.y / canvasSize.height * (CALENDAR_ROWS + rows)).toInt() + 1

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

            for (i in 1 until CALENDAR_ROWS + rows) {
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

            for (i in dateList.dayOfWeek until dateList.dayInMonth.size + dateList.dayOfWeek) {
                val textPositionX: Float
                textPositionX = xSteps * (i % CALENDAR_COLUMNS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth / 2
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        dateList.dayInMonth.get(i - dateList.dayOfWeek).day.toString(),
                        textPositionX,
                        textPositionY,
                        Paint().apply {
                            textSize = textHeight
                            color = if (dateList.month == todayDate.month &&
                                dateList.year == todayDate.year &&
                                dateList.dayInMonth.get(i - dateList.dayOfWeek).day == todayDate.dayInMonth[0].day
                            ) {

                                Color.Green.toArgb()
                            } else {
                                Color.White.toArgb()
                            }
                            isFakeBoldText = true
                        }
                    )
                }
                if (dateList.dayInMonth.get(i - dayOfWeek.value).training
                    ||
                    //Изменить canvasPar date нужно проверять день
                    trainingUpdate
                    //&& canvasPar.date == dateList.dayInMonth.get(i - dayOfWeek.value)
                    ) {

                    translate(
                        textPositionX + 35f,
                        textPositionY + 15f,
                    ) {
                        with(painter) {
                            draw(
                                size = Size(30.dp.toPx(), 30.dp.toPx()),
                                alpha = 1f,
                                colorFilter = ColorFilter.tint(Color.Green)
                            )
                        }
                    }
                }
                month.value = dateList.month
            }
        }
    }
}
