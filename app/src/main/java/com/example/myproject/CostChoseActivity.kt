package com.example.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_cost_chose.*

class CostChoseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_chose)
        //MyDbManager.getInstance(this).readColumn(DataBase.TABLE_OUTCOME_CATEGORY_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_NAME)

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MyDbManager.getInstance(this).readColumn(DataBase.TABLE_OUTCOME_CATEGORY_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_NAME))

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }


}
