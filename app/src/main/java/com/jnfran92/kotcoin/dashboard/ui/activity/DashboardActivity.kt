package com.jnfran92.kotcoin.dashboard.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.crypto.presentation.CryptoDetailsViewModel
import com.jnfran92.kotcoin.dashboard.presentation.DashboardViewModel
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS1UIState
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS2UIState
import com.jnfran92.kotcoin.dashboard.ui.compose.DashboardScreen
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isSystemDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember {
                mutableStateOf(isSystemDarkTheme)
            }
            KotcoinAppTheme(darkTheme = isDarkTheme) {
                DashboardView(viewModel = viewModel) {
                    Timber.d("onCreate: $isDarkTheme")
                    isDarkTheme = !isDarkTheme
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(viewModel: DashboardViewModel, onDarkThemeChange: (Boolean) -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val isSystemDarkTheme = isSystemInDarkTheme()
    var isDarkTheme by remember {
        mutableStateOf(isSystemDarkTheme)
    }
    val viewStateS1 by viewModel.viewStateS1.collectAsState()
    val viewStateS2 by viewModel.viewStateS2.collectAsState()

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
            dashboardS1UIState = viewStateS1,
            dashboardS2UIState = viewStateS2
        )
    }
}