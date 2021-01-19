package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{
    val myDbManager = MyDbManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        myDbManager.openDb()
        setContentView(R.layout.activity_main)


    }

    override fun onResume() {
        super.onResume()
        var sum = 0.0

        val List = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_VALUE)
        for (item in List) {
            sum += item.toFloat()
        }

        val dataList = myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_VALUE)
        for (item in dataList) {
            sum -= item.toFloat()
        }

        balance.text = sum.toString()
    }

    fun costMe (view: View){
        val costsIntent = Intent(this, CostChoseActivity::class.java)
        startActivity(costsIntent)

    }
    fun StatMe (view: View){
        val statIntent = Intent(this, StatActivity::class.java)
        startActivity(statIntent)
    }
    fun incomeMe (view: View){
        val incomeIntent = Intent(this, IncomeActivity::class.java)
        startActivity(incomeIntent)
    }
}
