package com.jnfran92.kotcoin.dashboard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.crypto.presentation.model.UICryptoDetails
import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.PI
import kotlin.math.sin

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

sealed interface UICryptoFavoriteTrending {
    object TrendingUp : UICryptoFavoriteTrending
    object TrendingDown : UICryptoFavoriteTrending
    object NotTrending : UICryptoFavoriteTrending
}

data class UICryptoFavorite(
    val id: Int,
    val name: String,
    val symbol: String,
    val price: UIPrice,
    val trending: UICryptoFavoriteTrending
)

data class UICryptoPopular(
    val id: Int,
    val name: String,
    val symbol: String,
    val historicalUIPrice: List<UIPrice>
)

data class UIDashboard(
    val listOfFavorites: List<UICryptoFavorite> = listOf(),
    val listOfPopular: List<UICryptoPopular> = listOf()
)

val uiCryptoFavorites = listOf(
    UICryptoFavorite(
        id = 1,
        name = "Bitcoin",
        symbol = "BTC",
        price = UIPrice(
            price = 30125.50,
            marketCap = 575900000000.0,
            volume24h = 15100000000.0,
            percentChange1h = 0.05,
            percentChange24h = -0.2,
            percentChange7d = 1.3,
            lastUpdated = "2023-07-28T14:50:00.000Z"
        ),
        trending = UICryptoFavoriteTrending.TrendingUp
    ),
    UICryptoFavorite(
        id = 2,
        name = "Ethereum",
        symbol = "ETH",
        price = UIPrice(
            price = 1885.75,
            marketCap = 226700000000.0,
            volume24h = 7450000000.0,
            percentChange1h = -0.02,
            percentChange24h = -0.3,
            percentChange7d = 0.5,
            lastUpdated = "2023-07-28T14:50:00.000Z"
        ),
        trending = UICryptoFavoriteTrending.TrendingDown
    ),
    UICryptoFavorite(
        id = 3,
        name = "Litecoin",
        symbol = "LTC",
        price = UIPrice(
            price = 885.75,
            marketCap = 226700000000.0,
            volume24h = 7450000000.0,
            percentChange1h = -0.02,
            percentChange24h = -0.3,
            percentChange7d = 0.5,
            lastUpdated = "2023-07-28T14:50:00.000Z"
        ),
        trending = UICryptoFavoriteTrending.TrendingDown
    ),
)
val uiCryptoPopulars = listOf(
    UICryptoPopular(
        id = 1,
        name = "Bitcoin",
        symbol = "BTC",
        historicalUIPrice = List(20) { index ->
            val value = sin(2 * PI * index / 20)
            UIPrice(
                price = value,
                marketCap = 226700000000.0,
                volume24h = 7450000000.0,
                percentChange1h = -0.02,
                percentChange24h = -0.3,
                percentChange7d = 0.5,
                lastUpdated = "2023-07-28T14:50:00.000Z"
            )
        }
    ),
    UICryptoPopular(
        id = 2,
        name = "Ethereum",
        symbol = "ETH",
        historicalUIPrice = List(20) { index ->
            val value = sin(2 * PI * index / 20)
            UIPrice(
                price = value,
                marketCap = 226700000000.0,
                volume24h = 7450000000.0,
                percentChange1h = -0.02,
                percentChange24h = -0.3,
                percentChange7d = 0.5,
                lastUpdated = "2023-07-28T14:50:00.000Z"
            )
        }
    )
)


@OptIn(ExperimentalFoundationApi::class)
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {

            Text(text = "Favoritos")
            Row {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2)
                ) {

                    items(uiCryptoFavorites) { item ->
                        Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "$${"%.${1}f".format(item.price.price / 1000)}K",
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Image(
                                        painter = painterResource(id =
                                        when(item.trending){
                                            UICryptoFavoriteTrending.NotTrending -> {
                                                R.drawable.baseline_arrow_drop_up_24
                                            }
                                            UICryptoFavoriteTrending.TrendingDown ->{
                                                R.drawable.baseline_arrow_drop_down_24
                                            }
                                            UICryptoFavoriteTrending.TrendingUp -> {
                                                R.drawable.baseline_arrow_drop_up_24
                                            }
                                        }
                                        ),
                                        contentDescription = "trending",
                                        modifier = Modifier.size(30.dp),
                                        colorFilter = ColorFilter.tint(
                                            when(item.trending){
                                                UICryptoFavoriteTrending.NotTrending -> {
                                                    Color.Blue
                                                }
                                                UICryptoFavoriteTrending.TrendingDown ->{
                                                    Color.Red
                                                }
                                                UICryptoFavoriteTrending.TrendingUp -> {
                                                    Color.Green
                                                }
                                            }
                                        )
                                    )
                                }
                                Text(
                                    text = item.symbol,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    text = item.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                }
            }

            Text(text = "Populares")

            Row {

                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 200.dp)
                ) {

                    items(uiCryptoPopulars) { item ->
                        Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = item.symbol,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    text = item.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Light
                                )
                                val entries = item.historicalUIPrice.mapIndexed { index, uiPrice ->
                                    Entry(index.toFloat(), uiPrice.price.toFloat())
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    LineChartCompose(item.historicalUIPrice)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun LineChartCompose(
    historicData: List<UIPrice>
) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                // Customize the chart here (e.g., set colors, labels, etc.)
                val entries = historicData.mapIndexed { index, uiPrice ->
                    Entry(index.toFloat(), uiPrice.price.toFloat())
                }

                val dataSet = LineDataSet(entries, null)
                dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
                dataSet.setDrawFilled(true)
                dataSet.setDrawCircles(false)
                dataSet.lineWidth = 3.0f
                dataSet.valueTextSize = 0.0f
                dataSet.setDrawValues(false)

                val lineData = LineData(dataSet)


                data = lineData
                legend.isEnabled = false
                description = null

                xAxis.setDrawGridLines(false)
                axisLeft.setDrawGridLines(false)
                axisRight.setDrawGridLines(false)

                xAxis.setDrawAxisLine(false)
                axisLeft.setDrawAxisLine(false)
                axisRight.setDrawAxisLine(false)
                axisRight.isEnabled = false

                axisRight.textColor = resources.getColor(R.color.white, null)
                axisLeft.textColor = resources.getColor(R.color.white, null)
                xAxis.textColor = resources.getColor(R.color.white, null)
                legend.textColor = resources.getColor(R.color.white, null)

                val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.US)

                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                        val date = formatter.parse(historicData[value.toInt()].lastUpdated)
                        return formatter.format(date ?: "")
                    }
                }
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.labelRotationAngle = 45.0f
                xAxis.isGranularityEnabled = true
                xAxis.granularity = 7f
                extraBottomOffset = 40f
                setTouchEnabled(true)
                setDrawBorders(false)
                setDrawGridBackground(false)
                invalidate()

            }
        },
        modifier = Modifier
            .fillMaxSize()
            .height(300.dp)
            .padding(10.dp)
    )
}



@Preview
@Composable
fun ComposablePreview() {
    MaterialTheme {
        DashboardView()
    }
}