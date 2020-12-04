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

        var sum=0.0

        //myDbManager.openDb()
        var List = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_CATEGORY_ID)

        var i=0
        for (id in List) {
            categoryIncome.append(myDbManager.getByID(id, DataBase.COLUMN_INCOME_CATEGORY_NAME, DataBase.TABLE_INCOME_CATEGORY_NAME) +"\n")
        }

        List = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_VALUE)
        for (item in List) {
            historyIncomeData.append(item + "\n")
            sum += item.toFloat()
        }

        List = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_DATE_NAME)
        for (item in List) {
            historyIncomeDate.append(item + "\n")
        }

        sumIncome.text = sum.toString()
    }

}
