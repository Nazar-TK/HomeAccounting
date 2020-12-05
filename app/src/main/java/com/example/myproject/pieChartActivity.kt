package com.example.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import kotlinx.android.synthetic.main.activity_pie_chart.*
import kotlin.random.Random

class pieChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()

        chart.setPieChart(pieChart)     //output piechart
        chart.showLegend(legendLayout)  //output legend

    }
    private fun provideSlices(): ArrayList<Slice> {        //builds slices for piechart

        return arrayListOf(
            Slice(
                10.toFloat(),
                R.color.colorPrimary,
                "Google"
            ),
            Slice(
                24.toFloat(),
                R.color.screenBackground,
                "Facebook"
            ),
            Slice(
               200.toFloat(),
                R.color.screenBackground3,
                "Twitter"
            ),
            Slice(
                45.toFloat(),
                R.color.colorAccent,
                "Other"
            )
        )
    }
}