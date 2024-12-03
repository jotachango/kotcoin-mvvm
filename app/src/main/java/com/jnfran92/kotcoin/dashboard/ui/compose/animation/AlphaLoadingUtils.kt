package com.jnfran92.kotcoin.dashboard.ui.compose.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush

@Composable
fun getAlphaBrush(alphaValue: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0f + alphaValue),
            MaterialTheme.colorScheme.primary.copy(alpha = 1f - alphaValue)
        )
    )
}

@Composable
fun getLoadingAlphaAnimation(label: String): Float {
    val infiniteTransition = rememberInfiniteTransition(label = label)
    val alphaValue by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = label
    )
    return alphaValue
}