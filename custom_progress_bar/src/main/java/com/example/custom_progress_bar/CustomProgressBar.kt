package com.example.custom_progress_bar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun StepProgressBar(
    currentStep: Int,
    steps: List<String>,
    stepCompletedColor: Color = Color(0xFFB24824),
    stepPendingColor: Color = Color.LightGray,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    iconSize: Dp = 40.dp,
    iconPadding: Dp = 8.dp,
    dividerThickness: Dp = 2.dp,
    dividerPadding: Dp = 22.dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        steps.forEachIndexed { index, step ->
            ConstraintLayout(
                modifier = Modifier
                    .weight(1f)
                    .padding(iconPadding)
            ) {
                val (iconItem, stepText, divider) = createRefs()

                // Step circle (icon)
                StepIcon(
                    isCompleted = index < currentStep,
                    isCurrent = index == currentStep,
                    completedColor = stepCompletedColor,
                    pendingColor = stepPendingColor,
                    size = iconSize,
                    modifier = Modifier.constrainAs(iconItem) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )

                // Step text (name of the step)
                Text(
                    text = step,
                    fontSize = 18.sp,
                    color = if (index <= currentStep) Color.Black else Color.Gray,
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .constrainAs(stepText) {
                            top.linkTo(iconItem.bottom)
                            start.linkTo(iconItem.start)
                            end.linkTo(iconItem.end)
                        }
                        .padding(vertical = 10.dp)
                )

                if (index != steps.lastIndex) {
                    DividerLine(
                        isCompleted = index < currentStep,
                        completedColor = stepCompletedColor,
                        pendingColor = stepPendingColor,
                        thickness = dividerThickness,
                        paddingEnd = dividerPadding,
                        modifier = Modifier.constrainAs(divider) {
                            start.linkTo(iconItem.end)
                            top.linkTo(iconItem.top)
                            bottom.linkTo(iconItem.bottom)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StepIcon(
    isCompleted: Boolean,
    isCurrent: Boolean,
    completedColor: Color,
    pendingColor: Color,
    size: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = if (isCompleted || isCurrent) completedColor else pendingColor,
                shape = CircleShape
            )
            .background(
                if (isCompleted) completedColor else Color.Transparent
            )
    ) {
        when {
            isCompleted -> {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            isCurrent -> {
                Canvas(modifier = Modifier.size(10.dp)) {
                    drawCircle(color = completedColor)
                }
            }
        }
    }
}

@Composable
fun DividerLine(
    isCompleted: Boolean,
    completedColor: Color,
    pendingColor: Color,
    thickness: Dp,
    paddingEnd: Dp,
    modifier: Modifier = Modifier
) {
    Divider(
        color = if (isCompleted) completedColor else pendingColor,
        thickness = thickness,
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .padding(end = paddingEnd)
    )
}
