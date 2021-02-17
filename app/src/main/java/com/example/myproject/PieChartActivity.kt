package com.example.myproject

import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import kotlinx.android.synthetic.main.activity_outcome_history.*
import kotlinx.android.synthetic.main.activity_pie_chart.*

class PieChartActivity : AppCompatActivity() {
    private val dbManager = DbManager.getInstance(this)
    private val pieColors = arrayOf(R.color.BackgroundCredits, R.color.blue,R.color.BackgroundTransport, R.color.BackgroundFood,
        R.color.BackgroundBlackThem, R.color.BackgroundCosts, R.color.darkOrange, R.color.RedLight,
        R.color.sapphire, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet)
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)
        startDate.text = (getCurrentDateTime().toString("yyyy/MM/dd").dropLast(2) + "01")
        endDate.text = getCurrentDateTime().toString("yyyy/MM/dd")

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()

        startCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> startDate.text = ("%02d/%02d/%02d").format(year ,month+1,dayOfMonth)})
        endCalendar.setOnDateChangeListener({TextView, year, month, dayOfMonth -> endDate.text = ("%02d/%02d/%02d").format(year ,month+1,dayOfMonth)})

//        chart.setPieChart(pieChart)
//        chart.showLegend(legendLayout)
        chart.setPieChart(pieChart)
        chart.showLegend(legendLayout,CustomLegendAdapter())
        fillFields()
    }

    private fun addSpases(s: String): Int {
        var res = 19 + (19 - s.length)
        return res
    }
    private fun fillFields() {
        OutcomeData.text.clear()
        var sum = 0.0
        val columns: Array<String> = arrayOf(
            "${DataBase.TABLE_OUTCOME_CATEGORY_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_NAME}",
            "${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_DATE}",
            "${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_VALUE}"
        )
        val groupBy =
            "${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_DATE}"
        val info: ArrayList<ArrayList<String>> =
            dbManager.tableOpenInformation(DataBase.TABLE_OUTCOME_NAME, columns, groupBy, startDate.text.toString(), endDate.text.toString())
        for (i in 0 until info.size) {
            val temp = info[i][0].padEnd(addSpases(info[i][0]),' ') + info[i][1] + " \t\t" + info[i][2]
            OutcomeData.append("${temp}\n\n")
        }

    }

    fun updateChart(view: View) {
        fillFields()
        summa.text = dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(), null).toString()

        val pieChart = PieChart(
            slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
        ).build()
//        chart.setPieChart(pieChart)     //output piechart
//        chart.showLegend(legendLayout)  //output legend
        chart.showLegend(legendLayout)
        chart.showLegend(legendLayout,CustomLegendAdapter())
        OutcomeData.editableText.clear()
        fillFields()
    }

    private fun provideSlices(): ArrayList<Slice> {
        val category = dbManager.readColumn(DataBase.TABLE_OUTCOME_CATEGORY_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_NAME)
        val slices = arrayListOf<Slice>()
        for (i in category.indices){
            slices.add(Slice(dbManager.sumForPeriod(DataBase.TABLE_OUTCOME_NAME, startDate.text.toString(),endDate.text.toString(),i+1),
                        pieColors[i],
                        category[i]))
        }
        return slices
    }

}