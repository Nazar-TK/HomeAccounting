package com.example.myproject

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_income.*

class IncomeActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
    }


//    incomeButton.setOnClickListener {
//        if (incomeField.text.isNotEmpty()) {
//            textSum.text = (textSum.text.toString().toInt() + incomeField.text.toString().toInt()).toString()
//            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
//
//            val data: Int
//            data = incomeField.text.toString().toInt()
//            incomeField.getText().clear()
//
//
//        }
//        else
//        {
//            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
//        }
//
//    }

    override fun onResume() {

        super.onResume()
        myDbManager.openDb()
        // val dataList = myDbManager.readDbData()

//        for (item in dataList)
//        {
//            tvTest.append(item)
//            tvTest.append("\n")
//        }
    }
    fun saveIncome(view: View) {
        if(incomeField.text.isNotEmpty()) {
            myDbManager.insertToDb(arrayListOf("1", incomeField.text.toString()),
                                                                DataBase.TABLE_INCOME_NAME)
            incomeField.text.clear()
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