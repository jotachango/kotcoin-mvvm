package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme

@Composable
fun DashboardSectionTitle(emoji: String, title: String) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = emoji,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun DashboardSectionTitlePreview() {
    KotcoinAppTheme {
        DashboardSectionTitle(emoji = "🚀", title = "Trending Now")
    }
}
