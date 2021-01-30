package com.example.myproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import kotlinx.android.synthetic.main.activity_pie_chart.*

class PieChartActivity : AppCompatActivity() {
    private val dbManager = DbManager.getInstance(this)

    var start=""
    var end=""

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)
        startDate.text = "20/01/2021"
        endDate.text = "30/01/2021"

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()

        startCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> startDate.text = "%d/%02d/%02d".format(dayOfMonth ,month+1,year)})
        endCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> endDate.text = "%d/%02d/%02d".format(dayOfMonth ,month+1,year)})

        chart.setPieChart(pieChart)     //output piechart
        chart.showLegend(legendLayout)  //output legend

    }

    fun updateChart(view: View) {
        summa.text = dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(), null).toString()

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()
        chart.setPieChart(pieChart)     //output piechart
        chart.showLegend(legendLayout)  //output legend

    }

    private fun provideSlices(): ArrayList<Slice> {        //builds slices for piechart

        return arrayListOf(
            Slice(
                dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),1),
                R.color.BackgroundCredits,
                "Кредити"
            ),
            Slice(
                dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),2),
                R.color.BackgroundFood,
                "Харчування"
            ),
            Slice(
                dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),3),
                R.color.BackgroundEntertainment,
                "Розваги"
            ),
            Slice(
                dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),4),
                R.color.BackgroundTransport,
                "Транспорт"
            ),
            Slice(
                dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),5),
                R.color.BackgroundUtilities,
                "Комунальні послуги"
            )
        )
    }

}