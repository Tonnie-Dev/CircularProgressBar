package com.uxstate.circularprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uxstate.circularprogressbar.ui.theme.CircularProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularProgressBarTheme {

            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
//boolean to monitor if the animation has played

    var animationPlayed by remember { mutableStateOf(false) }


    val currentPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )


    //add launchEffect

    LaunchedEffect(key1 = true, block = { animationPlayed = true })


    //Box container with canvas
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(radius * 2)) {

        //on canvas we can draw anything we want
        Canvas(modifier = Modifier.size(radius * 2), onDraw = {


            //inside the DrawScope we have multiple methods e.g. drawArc

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(),  cap = StrokeCap.Round)
            )

        })
    }

}