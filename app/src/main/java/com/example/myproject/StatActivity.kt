package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class StatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)
    }

    fun HistoryMe (view: View){
        val statIntent = Intent(this, IncomeHistory::class.java)
        startActivity(statIntent)
    }
    fun OutcomeHistoryMe (view: View){
        val HistoryOutcome = Intent(this,OutcomeHistory::class.java)
        startActivity(HistoryOutcome)
    }
    fun PieChartMe (view: View){
        val statIntent = Intent(this, pieChartActivity::class.java)
        startActivity(statIntent)
    }
}


