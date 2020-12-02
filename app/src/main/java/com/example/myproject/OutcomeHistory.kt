package com.example.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_income_history.*
import kotlinx.android.synthetic.main.activity_outcome_history.*


class OutcomeHistory : AppCompatActivity() {
    val myDbManager = MyDbManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outcome_history)
    }
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        var sum=0.0
        var i=0
        val categoryList = myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_ID)
        for (item in categoryList) {
            categoryOutcome.append(item)
            categoryOutcome.append("\n")
            sum += item.toFloat()
        }
        val dataList = myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_VALUE)
        for (item in dataList) {
            historyOutcomeData.append(item)
            historyOutcomeData.append("\n")
            sum += item.toFloat()
        }
        val dateList = myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_DATE_NAME)
        for (item in dateList) {
            historyOutcomeDate.append(item + "\n")
        }
        sumOutcome.text = sum.toString()
    }
}
