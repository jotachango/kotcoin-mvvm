package com.jnfran92.kotcoin.dashboard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard
import com.jnfran92.kotcoin.dashboard.ui.compose.DashboardScreen

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DashboardView()
            }
        }
    }
}

@Composable
fun DashboardView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Kotcoin")
                }
            )
        },
    ) { innerPadding ->
        DashboardScreen(
            innerPadding = innerPadding,
            uiDashboard = UIDashboard.DUMMY
        )
    }
}
@Preview
@Composable
fun ComposablePreview() {
    MaterialTheme {
        DashboardView()
    }
}