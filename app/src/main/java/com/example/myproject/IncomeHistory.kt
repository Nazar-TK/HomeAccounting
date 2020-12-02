package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_income_history.*

class IncomeHistory : AppCompatActivity() {

    val myDbManager = MyDbManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_history)
    }



    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        val categoryList = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_CATEGORY_ID)
        var sum=0.0
        var i=0
        for (item in categoryList) {
            categoryIncome.append(item)
            categoryIncome.append("\n")
        }

        val dataList = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_VALUE)
        for (item in dataList) {
            historyIncomeData.append(item)
            historyIncomeData.append("\n")
            sum += item.toFloat()
        }
        val dateList = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_DATE_NAME)
        for (item in dateList) {
            historyIncomeDate.append(item + "\n")
        }
            sumIncome.text = sum.toString()
        }

}
