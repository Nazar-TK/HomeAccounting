package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myDbManager = MyDbManager(this)
        balance.text = 0.toString()

    }


    fun costMe (view: View){
        val costsIntent = Intent(this, CostsActivity::class.java)
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
