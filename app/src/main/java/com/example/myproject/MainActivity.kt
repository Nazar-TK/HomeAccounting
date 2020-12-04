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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        balance.text = 0.toString()
        var empty = true
        var cursor = myDbManager.db?.rawQuery("SELECT COUNT(*) FROM TABLE_OUTCOME_CATEGORY_NAME", null)
        if (cursor != null && cursor.moveToFirst()) {
            empty= (cursor.getInt (0) == 0)
        }
        cursor?.close()


        cursor = myDbManager.db?.rawQuery("SELECT COUNT(*) FROM TABLE_INCOME_CATEGORY_NAME", null)
        if (cursor != null && cursor.moveToFirst()) {
            empty = (cursor.getInt (0) == 0)
        }
        cursor?.close()



        if(empty)
        {
            myDbManager.insertToDb(arrayListOf("salary"),DataBase.TABLE_INCOME_CATEGORY_NAME)
            myDbManager.insertToDb(arrayListOf("passive income"),DataBase.TABLE_INCOME_CATEGORY_NAME)
        }

    }


    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
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
