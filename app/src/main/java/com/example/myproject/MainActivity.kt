package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

//import MechaChmo.kt

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ma= MechaChmo()
        balance.text = ma.vel.toString()
    }


    fun costMe (view: View){
        val costsIntent = Intent(this, CostsActivity::class.java)
        startActivity(costsIntent)
        //var ma= MechaChmo()
        //var innt: Int
        //ma.vel = 15;
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
