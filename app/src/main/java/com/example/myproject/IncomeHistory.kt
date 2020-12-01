package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_income_history.*

class IncomeHistory : AppCompatActivity() {

    val myDbManager = MyDbManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_history)
    }



    override fun onResume() {
        super.onResume()

        myDbManager.openDb()
//        val dataList = myDbManager.readDbData(DataBase.TABLE_OUTCOME_CATEGORY_NAME)
//        var sum=0.0
//        var i=0
//        for (item in dataList) {
//
//                category.append(item)
//                category.append("\n")
////            } else {
////                category.append(item)
////                category.append("\n")
////            }
////            i++
//        }
//        category.append("\n")
        var i=0
        val outcomeList = myDbManager.readDbData(DataBase.TABLE_OUTCOME_NAME)
        for (item in outcomeList) {
            if (i < outcomeList.size / 2) {
                category.append(myDbManager.getParticularUserData(item))
                category.append("\n")
            } else {
                historyData.append(item)
                historyData.append("\n")
            }
            i++
        }
               // sum = sum + item.toFloat()
           // }
          //

        //}

       // summ.text = sum.toString()
    }

}
