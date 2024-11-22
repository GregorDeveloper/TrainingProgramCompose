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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.Date
import gregor.developer.trainingprogramcompose.data.static_data.DayTraining
import gregor.developer.trainingprogramcompose.screen.calendar_screen.data.CanvasParametr

import kotlinx.coroutines.launch

private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLUMNS = 7

@Composable
fun Calendar(
    modifier: Modifier,
    dateList: MutableState<Date>,
    onDayClick: (DayTraining) -> Unit,
    strokeWidth: Float = 7f,
    todayDate: Date,
    rows: Int,
    canvasPar: CanvasParametr,
    saveCanvasParameter: (CanvasParametr) -> Unit,
) {

    var clickDayColorText by remember {
        mutableStateOf(-1)
    }

    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }
    var clickAnimationOffset by remember {
        mutableStateOf(canvasPar.offset)
    }
    var animationRadius by remember {
        mutableStateOf(canvasPar.radios)
    }

    val listDt = remember {
        mutableStateOf(dateList)
    }
    val daysOfMonth = remember { mutableStateOf(dateList.value.dayInMonth) }
    val dayOfWeek = remember { mutableStateOf(0) }
    val month = remember { mutableStateOf(dateList.value.month) }
    val year = remember {
        mutableStateOf(dateList.value.year)
    }
    dayOfWeek.value = dateList.value.dayOfWeek
    daysOfMonth.value = dateList.value.dayInMonth
    val scope = rememberCoroutineScope()
    val clickDay = remember { mutableStateOf(-1) }
    val rowss = remember { mutableStateOf(0) }
    rowss.value = rows
    if (month.value != dateList.value.month
        || year.value != dateList.value.year
    ) {
        year.value = dateList.value.year
        month.value = dateList.value.month
        animationRadius = 0f
        clickDay.value = -1
    }
    val painter = painterResource(R.drawable.fitness_icon_8)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
//        Log.d()
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
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
                                        clickDayColorText = day
                                        clickDay.value = day
                                        clickAnimationOffset = offset
                                        animate(
                                            0f,
                                            525f,
                                            animationSpec = tween(100)
                                        ) { value, _ ->
                                            animationRadius = value
                                        }
                                        saveCanvasParameter(
                                            CanvasParametr(
                                                clickAnimationOffset,
                                                animationRadius,
                                                day.toString(),
                                                canvasPar.positionPic
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
                color = Color.Green,
                size = Size(width = canvasWidth, height = canvasHeight),
                cornerRadius = CornerRadius(25f, 25f), //обводка
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

            for (i in dateList.value.dayOfWeek until dateList.value.dayInMonth.size + dateList.value.dayOfWeek) {
                val textPositionX: Float
                textPositionX = xSteps * (i % CALENDAR_COLUMNS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth / 2
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                    //   listDt.value.value.dayInMonth.get(i - listDt.value.value.dayOfWeek).day.toString(),
                        dateList.value.dayInMonth.get(i - dateList.value.dayOfWeek).day.toString(),
                        textPositionX,
                        textPositionY,
                        Paint().apply {
                            textSize = textHeight
                            color = if (dateList.value.month == todayDate.month &&
                                dateList.value.year == todayDate.year &&
                                dateList.value.dayInMonth.get(i - dateList.value.dayOfWeek).day == todayDate.dayInMonth[0].day
                            ) {
                                Color.Green.toArgb()
                            } else {
                                Color.White.toArgb()
                            }
                            isFakeBoldText = true
                        }
                    )
                }
                if(daysOfMonth.value.get(i - dayOfWeek.value).training)
                        //(daysOfMonth.value.get(i - dayOfWeek.value).training)
                { // Изменить цвет при нажатии

                    translate(
                        textPositionX + 70f,
                        textPositionY - 20f
                               // canvasPar.positionPic
                        ,
                    ) {
                        with(painter) {
                            draw(
                                size = Size(20.dp.toPx(), 20.dp.toPx()),
                                alpha = 1f,
                                colorFilter = ColorFilter.tint(Color.Green)
                            )
                        }
                    }
                }
                month.value = dateList.value.month
            }
        }
    }
}
