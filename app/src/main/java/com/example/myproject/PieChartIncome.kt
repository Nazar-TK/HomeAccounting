package com.example.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import kotlinx.android.synthetic.main.activity_income_history.*
import kotlinx.android.synthetic.main.activity_pie_chart.*

class PieChartIncome : AppCompatActivity() {
        private val dbManager = DbManager.getInstance(this)
        private val pieColors = arrayOf(R.color.BackgroundCredits, R.color.blue,R.color.BackgroundTransport, R.color.BackgroundFood,
            R.color.BackgroundBlackThem, R.color.BackgroundCosts, R.color.darkOrange, R.color.RedLight,
            R.color.sapphire, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet, R.color.DarkViolet)
        override fun onCreate(savedInstanceState: Bundle?) {
            supportActionBar?.hide()
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_pie_chart_income)

            okI.setOnClickListener{
                updateChart()
                fillFields()
            }
            startDateI.text = (getCurrentDateTime().toString("yyyy/MM/dd").dropLast(2) + "01")
            endDateI.text = getCurrentDateTime().toString("yyyy/MM/dd")


            startCalendarI.setOnDateChangeListener({TextView, year, month, dayOfMonth -> startDateI.text = ("%02d/%02d/%02d").format(year ,month+1,dayOfMonth)})
            endCalendarI.setOnDateChangeListener({TextView, year, month, dayOfMonth -> endDateI.text = ("%02d/%02d/%02d").format(year ,month+1,dayOfMonth)})
            updateChart()
            fillFields()
        }

        private fun addSpases(s: String): Int {
            var res = 19 + (19 - s.length)
            return res
        }
        private fun fillFields() {
            OutcomeDataI.text.clear()
            var sum = 0.0
            val columns: Array<String> = arrayOf(
                "${DataBase.TABLE_INCOME_CATEGORY_NAME}.${DataBase.COLUMN_INCOME_CATEGORY_NAME}",
                "${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_DATE}",
                "${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_VALUE}"
            )
            val groupBy =
                "${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_DATE}"
            val info: ArrayList<ArrayList<String>> =
                dbManager.tableOpenInformation(DataBase.TABLE_INCOME_NAME, columns, groupBy, startDateI.text.toString(), endDateI.text.toString())
            for (i in 0 until info.size) {
                val temp = info[i][0].padEnd(addSpases(info[i][0]),' ') + info[i][1] + " \t\t" + info[i][2]
                OutcomeDataI.append("${temp}\n\n")
            }

        }

        fun updateChart() {
            summaI.text = dbManager.sumForPeriod(DataBase.TABLE_INCOME_NAME, startDateI.text.toString(),endDateI.text.toString(), null).toString()

            val pieChart = PieChart(
                slices = provideSlices(), clickListener = null, sliceStartPoint = 0f, sliceWidth = 80f         //build piechart
            ).build()

            chartI.setPieChart(pieChart)
            chartI.showLegend(legendLayoutI,CustomLegendAdapter())
            OutcomeDataI.editableText.clear()
        }

        private fun provideSlices(): ArrayList<Slice> {
            val category = dbManager.readColumn(
                DataBase.TABLE_INCOME_CATEGORY_NAME,
                DataBase.COLUMN_INCOME_CATEGORY_NAME
            )
            val slices = arrayListOf<Slice>()
            for (i in category.indices) {
                slices.add(
                    Slice(
                        dbManager.sumForPeriod(
                            DataBase.TABLE_INCOME_NAME,
                            startDateI.text.toString(),
                            endDateI.text.toString(),
                            i + 1
                        ),
                        pieColors[i],
                        category[i]
                    )
                )
            }
            return slices
        }
    }
