package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_income_history.*

class IncomeHistory : AppCompatActivity() {
    private val myDbManager = MyDbManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_history)
    }

    override fun onResume() {
        super.onResume()
        fillFields()
    }

    private fun fillFields(){
        var sum=0.0
        var i = 0

        val columns: Array<String> = arrayOf(
            "${DataBase.TABLE_INCOME_CATEGORY_NAME}.${DataBase.COLUMN_INCOME_CATEGORY_NAME}",
            "${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_DATE_NAME}",
            "${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_VALUE}"
        )
        val groupBy =
            "${DataBase.TABLE_INCOME_CATEGORY_NAME}.${DataBase.COLUMN_INCOME_CATEGORY_NAME}"
        val info: ArrayList<ArrayList<String>> =
            myDbManager.tableOpenInformation(DataBase.TABLE_INCOME_NAME, columns, groupBy)

        while (i < info.size) {
          //  categoryIncome.append("${info[i][0]}\n\n")
          //  historyIncomeDate.append("${info[i][1]}\n\n")
            var temp = info[i][0] + " \t\t" + info[i][1] + " \t\t" + info[i][2]
            historyIncomeData.append("${temp}\n\n")
            i++
        }

        val incomeValueList = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_VALUE)
        for (item in incomeValueList) {
            sum += item.toFloat()
        }

        sumIncome.text = sum.toString()
    }
}
