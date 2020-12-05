package com.example.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import kotlinx.android.synthetic.main.activity_pie_chart.*

class pieChartActivity : AppCompatActivity() {
    val myDbManager = MyDbManager.getInstance(this)

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
                myDbManager.getCategorySum("1", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundCredits,
                "Credits"
            ),
            Slice(
                myDbManager.getCategorySum("2", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundFood,
                "Food"
            ),
            Slice(
                myDbManager.getCategorySum("3", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundEntertainment,
                "Entertainment"
            ),
            Slice(
                myDbManager.getCategorySum("4", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundTransport,
                "Transport"
            ),
            Slice(
                myDbManager.getCategorySum("5", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundUtilities,
                "Utilities"
            )
        )
    }
}