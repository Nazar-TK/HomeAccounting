package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
import kotlinx.android.synthetic.main.activity_outcome_history.*


class OutcomeHistory : AppCompatActivity() {
    private val myDbManager = DbManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outcome_history)
    }
    override fun onResume() {
        super.onResume()
        fillFields()
    }

    // function to calculate num of spaces for output//
    private fun addSpases(s: String): Int {
        val res = 19 + (19 - s.length)
        return res
    }
    private fun fillFields() {
        var sum = 0.0

        val columns: Array<String> = arrayOf(
            "${DataBase.TABLE_OUTCOME_CATEGORY_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_NAME}",
            "${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_DATE}",
            "${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_VALUE}"
        )
        val groupBy =
            "${DataBase.TABLE_OUTCOME_CATEGORY_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_NAME}"
        val info: ArrayList<ArrayList<String>> =
            myDbManager.tableOpenInformation(DataBase.TABLE_OUTCOME_NAME, columns, groupBy)

        for (i in 0 until info.size) {
            val temp = info[i][0].padEnd(addSpases(info[i][0]),' ') + info[i][1] + " \t\t" + info[i][2]
            historyOutcomeData.append("${temp}\n\n")
        }

        val dataList =  myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_VALUE)
        for (item in dataList) {
            sum += item.toFloat()
        }
        sumOutcome.text = sum.toString()
    }
}
