package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavoriteTrending
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DashboardScreen(innerPadding: PaddingValues, uiDashboard: UIDashboard) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
    ) {
        item {
            Text(text = "Favoritos")
        }

        item {
            LazyRow {
                items(uiDashboard.listOfFavorites) { item ->
                    Card(modifier = Modifier.padding(8.dp)) {
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
                                    painter = painterResource(
                                        id =
                                        when (item.trending) {
                                            UICryptoFavoriteTrending.NotTrending -> {
                                                androidx.core.R.drawable.ic_call_answer_low
                                            }

                                            UICryptoFavoriteTrending.TrendingDown -> {
                                                androidx.core.R.drawable.ic_call_answer_low
                                            }

                                            UICryptoFavoriteTrending.TrendingUp -> {
                                                androidx.core.R.drawable.ic_call_answer_low
                                            }
                                        }
                                    ),
                                    contentDescription = "trending",
                                    modifier = Modifier.size(30.dp),
                                    colorFilter = ColorFilter.tint(
                                        when (item.trending) {
                                            UICryptoFavoriteTrending.NotTrending -> {
                                                Color.Blue
                                            }

                                            UICryptoFavoriteTrending.TrendingDown -> {
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

        item {
            Text(text = "Populares")
        }
        items(uiDashboard.listOfPopular) { item ->
            Card(modifier = Modifier.padding(8.dp)) {
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LineChartCompose(item.historicalUIPrice)
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