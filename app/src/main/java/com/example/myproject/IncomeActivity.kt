package com.example.myproject

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
    }

    private fun saveIncome(category_id: Int, sum: Float) {
        val myDbManager = DbManager.getInstance(this)
        val incomeEvent = IncomeEvent(category_id, sum, getCurrentDateTime().toString("yyyy/MM/dd"))
        myDbManager.insertToDb(incomeEvent, DataBase.TABLE_INCOME_NAME)
        currentBalance += sum
    }

    fun safeSaveIncome(view: View){
        val ONE_TIME_INCOME = 1
        if(incomeField.text.isNotEmpty()) {
            saveIncome(ONE_TIME_INCOME, incomeField.text.toString().toFloat()) //We have only 1 income category yet
            incomeField.text.clear()
            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
    }
}