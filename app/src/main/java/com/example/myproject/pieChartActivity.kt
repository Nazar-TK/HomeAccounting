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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()

        startCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> startDate.text = "%02d/%02d/%d".format(dayOfMonth,month+1,year)})
        endCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> endDate.text = "%02d/%02d/%d".format(dayOfMonth,month+1,year)})

    }


    fun updateChart(view: View) {
        val smt = myDbManager.readDbData(DataBase.TABLE_OUTCOME_NAME,startDate.text.toString(),endDate.text.toString())
        sm.text=""
        for (item in smt) {
            sm.append(item + "\n")
        }
    }


    private fun provideSlices(): ArrayList<Slice> {        //builds slices for piechart

        return arrayListOf(
            Slice(
                myDbManager.getCategorySum("1", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundCredits,
                "Кредити"
            ),
            Slice(
                myDbManager.getCategorySum("2", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundFood,
                "Харчування"
            ),
            Slice(
                myDbManager.getCategorySum("3", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundEntertainment,
                "Розваги"
            ),
            Slice(
                myDbManager.getCategorySum("4", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundTransport,
                "Транспорт"
            ),
            Slice(
                myDbManager.getCategorySum("5", DataBase.COLUMN_OUTCOME_VALUE, DataBase.TABLE_OUTCOME_NAME).toFloat(),
                R.color.BackgroundUtilities,
                "Комунальні послуги"
            )
        )
    }




}