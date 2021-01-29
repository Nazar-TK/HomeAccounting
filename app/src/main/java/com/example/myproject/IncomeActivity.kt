package com.example.myproject

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_income.*
import java.text.SimpleDateFormat
import java.util.*

class IncomeActivity : AppCompatActivity() {

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    val myDbManager = MyDbManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
    }
    override fun onResume() {

        super.onResume()
        myDbManager.openDb()

    }
    fun saveIncome(view: View) {
        if(incomeField.text.isNotEmpty()) {
            myDbManager.insertToDb(arrayListOf("1", incomeField.text.toString(),  getCurrentDateTime().toString("dd/MM/yyyy")), DataBase.TABLE_INCOME_NAME)
            incomeField.text.clear()
            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}