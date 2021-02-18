package com.example.myproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DbManager
import com.example.myproject.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlin.math.roundToInt

var currentBalance = 0f

class MainActivity : AppCompatActivity()
{
    private val dbManager = DbManager.getInstance(this)
    private lateinit var pref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pref = getSharedPreferences("SharedPreferences4currentBalance", MODE_PRIVATE)
        currentBalance = pref.getFloat("current_balance", 0.2f)
        dbManager.openDb()
    }

    override fun onResume() {
        super.onResume()

        pref.edit().putFloat("current_balance", currentBalance).apply()
        balance.text = ((currentBalance * 100.0).roundToInt() /100.0).toString()
    }

    fun costMe (view: View){
        val costsIntent = Intent(this, CostChoseActivity::class.java)
        startActivity(costsIntent)
    }
    
    fun statMe (view: View){
        val statIntent = Intent(this, StatActivity::class.java)
        startActivity(statIntent)
    }
    
    fun incomeMe (view: View){
        val incomeIntent = Intent(this, IncomeActivity::class.java)
        startActivity(incomeIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

    fun settings (view: View){
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

}
