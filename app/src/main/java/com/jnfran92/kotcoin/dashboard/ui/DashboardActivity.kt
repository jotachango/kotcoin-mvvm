package com.jnfran92.kotcoin.dashboard.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard
import com.jnfran92.kotcoin.dashboard.ui.compose.DashboardScreen
import timber.log.Timber

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isSystemDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember {
                mutableStateOf(isSystemDarkTheme)
            }
            KotcoinAppTheme(darkTheme = isDarkTheme) {
                DashboardView {
                    Timber.d("onCreate: $isDarkTheme")
                    isDarkTheme = !isDarkTheme
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(onDarkThemeChange: (Boolean) -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val isSystemDarkTheme = isSystemInDarkTheme()
    var isDarkTheme by remember {
        mutableStateOf(isSystemDarkTheme)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                title = {
                    Text(
                        "Kotcoin",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = {
                        isDarkTheme = !isDarkTheme
                        onDarkThemeChange.invoke(isDarkTheme)
                    }) {
                        Icon(
                            painter = painterResource(id = if (isDarkTheme) R.drawable.baseline_light_mode_24 else R.drawable.baseline_nightlight_24),
                            contentDescription = if (isDarkTheme) "Light Mode" else "Dark Mode"
                        )
                    }
                }
            )
        }
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
    KotcoinAppTheme() {
        DashboardView()
    }
}