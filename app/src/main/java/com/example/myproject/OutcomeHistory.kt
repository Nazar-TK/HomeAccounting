package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_outcome_history.*


class OutcomeHistory : AppCompatActivity() {
    val myDbManager = MyDbManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outcome_history)
    }
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        var sum=0.0
        var i=0

        val categoryList = myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_ID)
        for (id in categoryList) {
            categoryOutcome.append(myDbManager.getByID(id, DataBase.COLUMN_OUTCOME_CATEGORY_NAME, DataBase.TABLE_OUTCOME_CATEGORY_NAME) + "\n" + "\n")
        }

        val dataList = myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_VALUE)
        for (item in dataList) {
            historyOutcomeData.append(item + "\n" + "\n")
            sum += item.toFloat()
        }

        val dateList = myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_DATE_NAME)
        for (item in dateList) {
            historyOutcomeDate.append(item + "\n" + "\n")
        }
        sumOutcome.text = sum.toString()
    }
}
