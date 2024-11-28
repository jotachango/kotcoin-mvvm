package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS1UIState
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS2UIState
import com.jnfran92.kotcoin.dashboard.ui.compose.child.DashboardSectionTitle
import com.jnfran92.kotcoin.dashboard.ui.compose.child.FavoriteItem
import com.jnfran92.kotcoin.dashboard.ui.compose.child.PopularItem

@Composable
fun DashboardScreen(
    innerPadding: PaddingValues,
    dashboardS1UIState: DashboardS1UIState,
    dashboardS2UIState: DashboardS2UIState
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        // Section I
        item {
            DashboardSectionTitle("⭐", "Favorites")
        }
        when (dashboardS1UIState) {
            is DashboardS1UIState.ShowDataView -> {
                item {
                    LazyRow {
                        items(dashboardS1UIState.data.listOfFavorites) { item ->
                            FavoriteItem(item, modifier = Modifier
                                .padding(8.dp)
                                .padding(bottom = 16.dp)
                                .width(170.dp))
                        }
                    }
                }
            }

            DashboardS1UIState.ShowDefaultView -> {

            }

            is DashboardS1UIState.ShowErrorRetryView -> {
                item {
                    ErrorOnSection(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(150.dp)
                    )
                }
            }

            DashboardS1UIState.ShowLoadingView -> {
                item {
//                    LazyRow(modifier = Modifier.blur(10.dp)) {
//                        items(UIDashboardS1.DUMMY.listOfFavorites) { item ->
//                            FavoriteItem(item)
//                        }
//                    }
//                    AnimatedBlurLazyRow()
                    AnimatedLoadingPlaceholder()
                }
            }
        }

        // Section II
        item {
            DashboardSectionTitle("\uD83D\uDE80", "Populars")
        }
        when (dashboardS2UIState) {
            is DashboardS2UIState.ShowDataView -> {
                items(dashboardS2UIState.data.listOfPopular) { item ->
                    PopularItem(item)
                }
            }

            DashboardS2UIState.ShowDefaultView -> {
            }

            is DashboardS2UIState.ShowErrorRetryView -> {
                item {
                    ErrorOnSection(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(300.dp)
                    )
                }
            }

            DashboardS2UIState.ShowLoadingView -> {

            }
        }
    }
}

@Composable
fun AnimatedBlurLazyRow() {
    val infiniteTransition = rememberInfiniteTransition(label = "A")

    val blurRadius by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = 14f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "A"
    )

    LazyRow() {
        items(UIDashboardS1.DUMMY.listOfFavorites) { item ->
            FavoriteItem(item, modifier = Modifier
                .padding(8.dp)
                .padding(bottom = 16.dp)
                .width(170.dp)
                .blur(blurRadius.dp, BlurredEdgeTreatment.Unbounded))
        }
    }
}

@Composable
fun AnimatedLoadingPlaceholder() {
    // Transición infinita para animar opacidad
    val infiniteTransition = rememberInfiniteTransition(label = "B")

    // Animación de opacidad entre 0.3f y 1f
    val alphaValue by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "B"
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(UIDashboardS1.DUMMY.listOfFavorites) { _ ->
            // Item de placeholder animado
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(bottom = 16.dp)
                    .width(170.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Gray.copy(alpha = 0.1f),
                                Color.LightGray.copy(alpha = 0.9f)
                            )
                        )
                    )
                    .alpha(alphaValue) // Animación de opacidad
            )
        }
    }
}


@Composable
private fun ErrorOnSection(
    modifier: Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "😶‍🌫️ Ups!",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Text(
                text = "Something went wrong, Try again!",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            )
            Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
                Text(text = "Retry")
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun ComposablePreview() {
    KotcoinAppTheme {
        DashboardScreen(
            innerPadding = PaddingValues.Absolute(),
//            dashboardS1UIState = DashboardS1UIState.ShowDataView(UIDashboardS1.DUMMY),
//            dashboardS1UIState = DashboardS1UIState.ShowErrorRetryView(Exception()),
            dashboardS1UIState = DashboardS1UIState.ShowLoadingView,
//            dashboardS2UIState = DashboardS2UIState.ShowDataView(UIDashboardS2.DUMMY),
            dashboardS2UIState = DashboardS2UIState.ShowErrorRetryView(Exception()),
        )
    }
}