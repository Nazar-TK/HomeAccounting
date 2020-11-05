package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocts)
    }

    fun creditMe (view: View){
        val creditIntent = Intent(this, CreditsActivity::class.java)
        startActivity(creditIntent)
    }
    fun utiliteMe (view: View){
        val utiliteIntent = Intent(this, UtilitiesActivity::class.java)
        startActivity(utiliteIntent)
    }
    fun funMe (view: View){
        val funIntent = Intent(this, FunActivity::class.java)
        startActivity(funIntent)
    }
    fun transportMe (view: View){
        val transportIntent = Intent(this, TransportActivity::class.java)
        startActivity(transportIntent)
    }
    fun foodMe (view: View){
        val foodIntent = Intent(this, FoodActivity::class.java)
        startActivity(foodIntent)
    }

}
