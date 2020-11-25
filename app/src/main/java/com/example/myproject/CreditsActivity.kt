package com.example.myproject

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        if(creditSum.text.isNotEmpty()) {
            myDbManager.insertToDb(
                arrayListOf("1", creditSum.text.toString()),
                DataBase.TABLE_OUTCOME_NAME
            )
            creditSum.text.clear()
            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}
