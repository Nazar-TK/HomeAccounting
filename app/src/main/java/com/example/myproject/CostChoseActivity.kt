package com.example.myproject
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myproject.db.DataBase
import com.example.myproject.db.MyDbManager
import kotlinx.android.synthetic.main.activity_cost_chose.*
import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}
fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

class CostChoseActivity : AppCompatActivity() {
    private var currentChose = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_chose)

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
            MyDbManager.getInstance(this).readColumn(DataBase.TABLE_OUTCOME_CATEGORY_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_NAME))

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentChose = position + 1
            }
        }
    }

    private fun saveOutcome(idOfCategory: Int, sum: String){

        MyDbManager.getInstance(this).insertToDb(
            arrayListOf(
                idOfCategory.toString(),
                sum,
                getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss")
            ),
            DataBase.TABLE_OUTCOME_NAME
        )
    }

    fun safeSaveOutcome(view: View){

        if(editText.text.isNotEmpty()) {
            saveOutcome(currentChose, editText.text.toString())
            editText.text.clear()
            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
    }

}
