package com.jnfran92.kotcoin.dashboard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()) {

            Text(text = "Favoritos")
            Row {

                listOf("BTC", "ETH", "XX", "weqw").map {
                    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
                        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "$10", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = it)
                        }
                    }
                }

            }

            Text(text = "Populares")

            Row {



            }


        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    MaterialTheme {
        DashboardView()
    }
}