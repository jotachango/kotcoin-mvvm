package com.jnfran92.kotcoin.dashboard.ui.compose.children

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorOnSection(
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

@Composable
@Preview(showBackground = true)
fun ErrorOnSectionPreview() {
    ErrorOnSection(
        modifier = Modifier
            .padding(8.dp)
            .height(300.dp)
    )
}