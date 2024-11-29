package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS1UIState
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS2UIState
import com.jnfran92.kotcoin.dashboard.ui.compose.animation.getLoadingAlphaAnimation
import com.jnfran92.kotcoin.dashboard.ui.compose.children.DashboardSectionTitle
import com.jnfran92.kotcoin.dashboard.ui.compose.children.ErrorOnSection
import com.jnfran92.kotcoin.dashboard.ui.compose.children.FavoriteItem
import com.jnfran92.kotcoin.dashboard.ui.compose.children.PopularItem

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
            Section1(dashboardS1UIState)
        }
        
        // Section II
        item {
            DashboardSectionTitle("\uD83D\uDE80", "Populars")
            Section2(dashboardS2UIState)
        }
    }
}

@Composable
private fun Section1(dashboardS1UIState: DashboardS1UIState) {
    when (dashboardS1UIState) {
        is DashboardS1UIState.ShowDataView -> {
            LazyRow {
                items(dashboardS1UIState.data.listOfFavorites) { item ->
                    FavoriteItem(
                        item, modifier = Modifier
                            .padding(8.dp)
                            .padding(bottom = 16.dp)
                            .width(170.dp)
                            .height(150.dp)
                    )
                }
            }
        }

        DashboardS1UIState.ShowDefaultView -> {
            LazyRow {
                items(3) { _ ->
                    FavoriteItem(
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(bottom = 16.dp)
                            .width(170.dp)
                            .height(150.dp)
                    )
                }
            }
        }

        is DashboardS1UIState.ShowErrorRetryView -> {
            ErrorOnSection(
                modifier = Modifier
                    .padding(8.dp)
                    .height(150.dp)
            )
        }

        DashboardS1UIState.ShowLoadingView -> {
            LazyRow {
                items(3) { _ ->
                    FavoriteItem(
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(bottom = 16.dp)
                            .width(170.dp)
                            .height(150.dp),
                        alphaValue = getLoadingAlphaAnimation("DashboardScreen")
                    )
                }

            }
        }
    }
}

@Composable
private fun Section2(dashboardS2UIState: DashboardS2UIState) {
    when (dashboardS2UIState) {
        is DashboardS2UIState.ShowDataView -> {
            dashboardS2UIState.data.listOfPopular.forEach { item ->
                PopularItem(
                    item,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(bottom = 8.dp)
                        .height(150.dp)
                )
            }
        }

        DashboardS2UIState.ShowDefaultView -> {
            (1..3).forEach { _ ->
                PopularItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(bottom = 8.dp)
                        .height(150.dp)
                )
            }
        }

        is DashboardS2UIState.ShowErrorRetryView -> {
                ErrorOnSection(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(300.dp)
                )

        }

        DashboardS2UIState.ShowLoadingView -> {
            (1..3).forEach { _ ->
                PopularItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(bottom = 8.dp)
                        .height(150.dp),
                    alphaValue = getLoadingAlphaAnimation("DashboardScreen")
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun DashboardScreenPreview() {
    KotcoinAppTheme {
        DashboardScreen(
            innerPadding = PaddingValues.Absolute(),
            dashboardS1UIState = DashboardS1UIState.ShowDataView(UIDashboardS1.DUMMY),
            dashboardS2UIState = DashboardS2UIState.ShowDataView(UIDashboardS2.DUMMY)
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun DashboardScreenLoadingPreview() {
    KotcoinAppTheme {
        DashboardScreen(
            innerPadding = PaddingValues.Absolute(),
            dashboardS1UIState = DashboardS1UIState.ShowLoadingView,
            dashboardS2UIState = DashboardS2UIState.ShowLoadingView
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun DashboardScreenDefaultPreview() {
    KotcoinAppTheme {
        DashboardScreen(
            innerPadding = PaddingValues.Absolute(),
            dashboardS1UIState = DashboardS1UIState.ShowDefaultView,
            dashboardS2UIState = DashboardS2UIState.ShowDefaultView
        )
    }
}