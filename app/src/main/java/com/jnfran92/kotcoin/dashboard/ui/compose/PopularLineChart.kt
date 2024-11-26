package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard

@Composable
fun PopularLineChart(
    historicData: List<UIPrice>
) {
    val textColor = MaterialTheme.colorScheme.secondary.toArgb()
    val linesColor = MaterialTheme.colorScheme.tertiary.toArgb()
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
                dataSet.lineWidth = 1.0f
                dataSet.valueTextSize = 0.0f
                dataSet.setColor(linesColor)
                dataSet.fillColor = linesColor
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
                axisLeft.isEnabled = false

                xAxis.isEnabled = false

                axisLeft.textColor = textColor
                xAxis.textColor = textColor
                legend.textColor = textColor

                setTouchEnabled(false)
                setDrawBorders(false)
                setDrawGridBackground(false)
                invalidate()

            }
        },
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ChartPreview() {
    KotcoinAppTheme {
        PopularLineChart(
            historicData = UIDashboard.DUMMY.listOfPopular.first().historicalUIPrice
        )
    }
}