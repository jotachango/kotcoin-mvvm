package com.jnfran92.kotcoin.dashboard.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
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

@OptIn(ExperimentalMaterial3Api::class)
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
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun ComposablePreview() {
    KotcoinAppTheme {
        DashboardView()
    }
}