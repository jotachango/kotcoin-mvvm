package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard
import com.jnfran92.kotcoin.dashboard.ui.compose.child.DashboardSectionTitle
import com.jnfran92.kotcoin.dashboard.ui.compose.child.FavoriteItem
import com.jnfran92.kotcoin.dashboard.ui.compose.child.PopularItem

@Composable
fun DashboardScreen(innerPadding: PaddingValues, uiDashboard: UIDashboard) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        item {
            DashboardSectionTitle("⭐", "Favorites")
        }

        item {
            LazyRow {
                items(uiDashboard.listOfFavorites) { item ->
                    FavoriteItem(item)
                }
            }
        }

        item {
            DashboardSectionTitle("\uD83D\uDE80", "Populars")
        }
        items(uiDashboard.listOfPopular) { item ->
            PopularItem(item)
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun ComposablePreview() {
    KotcoinAppTheme {
        DashboardScreen(
            innerPadding = PaddingValues.Absolute(),
            uiDashboard = UIDashboard.DUMMY
        )
    }
}