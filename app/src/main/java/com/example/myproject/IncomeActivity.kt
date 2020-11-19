package com.example.myproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_income.*

class IncomeActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
    }
//        button.setOnClickListener {
//            if (editText.text.isNotEmpty()) {
//                textSum.text =
//                    (textSum.text.toString().toInt() + editText.text.toString().toInt()).toString()
//                Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
//
//                val data: Int
//                data = editText.text.toString().toInt()
//                editText.getText().clear()
//
//
//            } else
//                Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
//        }
//
//    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        val dataList = myDbManager.readDbData("income", "income value")

        for (item in dataList)
        {
            tvTest.append(item)
            tvTest.append("\n")
        }
    }
    fun onClickSave(view: View) {
        tvTest.text = ""

        myDbManager.insertToDb(editText.text.toString(), "income", "income value")

        val dataList = myDbManager.readDbData("income", "income value")

        for (item in dataList)
        {
            tvTest.append(item)
            tvTest.append("\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}