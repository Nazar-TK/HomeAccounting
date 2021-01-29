package com.example.myproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import kotlinx.android.synthetic.main.activity_pie_chart.*

class pieChartActivity : AppCompatActivity() {
    val myDbManager = MyDbManager.getInstance(this)

    var start=""
    var end=""

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()

        startCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> startDate.text = "%d/%02d/%02d".format(year,month+1,dayOfMonth)})
        endCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> endDate.text = "%d/%02d/%02d".format(year,month+1,dayOfMonth)})

        chart.setPieChart(pieChart)     //output piechart
        chart.showLegend(legendLayout)  //output legend

    }


    fun updateChart(view: View) {


        summa.text = myDbManager.readDbData1(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString()).toString()

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()
        chart.setPieChart(pieChart)     //output piechart
        chart.showLegend(legendLayout)  //output legend

    }

    private fun provideSlices(): ArrayList<Slice> {        //builds slices for piechart

        return arrayListOf(
            Slice(
                myDbManager.calendarData(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),"1").toFloat(),
                R.color.BackgroundCredits,
                "Кредити"
            ),
            Slice(
                myDbManager.calendarData(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),"2").toFloat(),
                R.color.BackgroundFood,
                "Харчування"
            ),
            Slice(
                myDbManager.calendarData(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),"3").toFloat(),
                R.color.BackgroundEntertainment,
                "Розваги"
            ),
            Slice(
                myDbManager.calendarData(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),"4").toFloat(),
                R.color.BackgroundTransport,
                "Транспорт"
            ),
            Slice(
                myDbManager.calendarData(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),"5").toFloat(),
                R.color.BackgroundUtilities,
                "Комунальні послуги"
            )
        )
    }

//    private fun provideSlices(): ArrayList<Slice> {        //builds slices for piechart
//
//        return arrayListOf(
//            Slice(
//                myDbManager.getCategorySum("1", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
//                R.color.BackgroundCredits,
//                "Кредити"
//            ),
//            Slice(
//                myDbManager.getCategorySum("2", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
//                R.color.BackgroundFood,
//                "Харчування"
//            ),
//            Slice(
//                myDbManager.getCategorySum("3", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
//                R.color.BackgroundEntertainment,
//                "Розваги"
//            ),
//            Slice(
//                myDbManager.getCategorySum("4", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
//                R.color.BackgroundTransport,
//                "Транспорт"
//            ),
//            Slice(
//                myDbManager.getCategorySum("5", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
//                R.color.BackgroundUtilities,
//                "Комунальні послуги"
//            )
//        )
//    }




}