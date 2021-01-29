package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_outcome_history.*


class OutcomeHistory : AppCompatActivity() {
    private val myDbManager = MyDbManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outcome_history)
    }
    override fun onResume() {
        super.onResume()
        fillFields()
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

        for (i in info.indices) {
            categoryOutcome.append("${info[i][0]}\n\n")
            historyOutcomeDate.append("${info[i][1]}\n\n")
            historyOutcomeData.append("${info[i][2]}\n\n")
        }

        val dataList =  myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_VALUE)
        for (item in dataList) {
            sum += item.toFloat()
        }
        sumOutcome.text = sum.toString()
    }
}
