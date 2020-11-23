package com.example.myproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_food1.*

class FoodActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food1)
    }
    val myDbManager = MyDbManager(this)
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()

    }
    fun saveFood(view: View) {
        myDbManager.insertToDb(arrayListOf("2",foodSum.text.toString()), DataBase.TABLE_OUTCOME_NAME)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}
