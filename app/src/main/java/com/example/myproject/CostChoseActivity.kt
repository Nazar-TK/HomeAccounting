package com.example.myproject
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
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
//    private val arrOfColors = arrayOf(R.color.BackgroundCredits, R.color.BackgroundFood,
//        R.color.BackgroundEntertainment, R.color.BackgroundTransport, R.color.BackgroundUtilities)

    private val arrOfImages = arrayOf(R.drawable.credyt, R.drawable.dishes,
        R.drawable.activities, R.drawable.transport, R.drawable.utiltties)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_chose)

        spinner.adapter = ArrayAdapter<String>(this, R.layout.style_spinner,
            DbManager.getInstance(this).readColumn(DataBase.TABLE_OUTCOME_CATEGORY_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_NAME))
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentChose = position + 1
                changeColor()
                changeImage()
            }
        }
    }

    private fun changeImage(){
        imageView.setImageResource(arrOfImages[currentChose - 1])
    }
/*
    private fun changeColor(){
        layout.setBackgroundResource(arrOfColors[currentChose - 1])
    }
*/
    private fun changeColor(){
        layout.setBackgroundResource(R.drawable.setting)
    }
    private fun saveOutcome(idOfCategory: Int, sum: Float){
        val dbManager = DbManager.getInstance(this)
        val outcomeEvent = OutcomeEvent(idOfCategory, sum, getCurrentDateTime().toString("yyyy/MM/dd"))
        dbManager.insertToDb(outcomeEvent, DataBase.TABLE_OUTCOME_NAME)
        currentBalance -= sum
    }

    fun safeSaveOutcome(view: View){

        if(editText.text.isNotEmpty()) {
            saveOutcome(currentChose, editText.text.toString().toFloat())
            editText.text.clear()
            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
    }

}
