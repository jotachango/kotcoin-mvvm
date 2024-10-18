package com.jnfran92.kotcoin.dashboard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.TestModifierUpdaterLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jnfran92.kotcoin.R

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            SimpleComposable()

            Table(listOfItems = listOf("A","B","C"))
        }
    }
}


@Composable
fun SimpleComposable() {
    Row{
        Image(
            painter = painterResource(R.drawable.ic_baseline_attach_money_24),
            contentDescription = "Contact profile picture",
        )
        Column {
            Text("Hello World 1")
            Text("Hello World 2")
        }
        Column {
            Text("Hello")
            Text("Hello Thor")
        }
    }
}

@Composable
fun Table(listOfItems: List<String>) {
    Column {
        listOfItems.mapIndexed { index, s ->
            Row {
                Text(text = index.toString())
                Text(text = s)
            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    Table(listOfItems = listOf("A","B","C"))
}