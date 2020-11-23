package com.example.myproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_credits.*


class CreditsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
    }

    val myDbManager = MyDbManager(this)
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()

    }
    fun saveCredit(view: View) {
        myDbManager.insertToDb(arrayListOf("1",creditSum.text.toString()), DataBase.TABLE_OUTCOME_NAME)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}
