package com.example.myproject
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
import kotlinx.android.synthetic.main.activity_cost_chose.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}
fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

class CostChoseActivity : AppCompatActivity() {
    private var currentChose = 0
    private var stringsInSpinner: ArrayList<String> = arrayListOf("")
    private val dbManager = DbManager.getInstance(this)
    private val arrOfImages = arrayOf(R.drawable.credyt, R.drawable.dishes,
        R.drawable.activities, R.drawable.transport, R.drawable.utiltties)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_chose)
        updateSpinner()

        tempbutton.setOnClickListener {
            callDialog()
        }

        outcomeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentChose = position
                changeImage()
            }
        }

        stringsInSpinner = DbManager.getInstance(this).readColumn(DataBase.TABLE_OUTCOME_CATEGORY_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_NAME)


        outcomeSpinner.adapter = object : ArrayAdapter<String> (this, R.layout.style_spinner, stringsInSpinner){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val tv = super.getDropDownView(position, convertView, parent) as TextView
                if (position == stringsInSpinner.size - 1)
                    tv.setBackgroundColor(Color.RED);
                return tv
            }
        }
    }

    private fun changeImage(){
        if(currentChose in arrOfImages.indices)
            outcomeImageView.setImageResource(arrOfImages[currentChose])
        else
            outcomeImageView.setImageResource(R.drawable.outcome)
    }


    private fun callDialog(){
        val promptsView = LayoutInflater.from(this).inflate(R.layout.prompt, null);
        val mDialogBuilder = AlertDialog.Builder(this);
        val userInput = promptsView.findViewById<EditText>(R.id.input_text);

        mDialogBuilder.setView(promptsView);

        mDialogBuilder
            .setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                dbManager.insertToDb(OutcomeCategoryEvent(userInput.text.toString()), DataBase.TABLE_OUTCOME_CATEGORY_NAME)
                updateSpinner()
            }
            .setNegativeButton("Назад"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            }

        val alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }

    private fun updateSpinner(){
        stringsInSpinner = DbManager.getInstance(this).readColumn(DataBase.TABLE_OUTCOME_CATEGORY_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_NAME)
    }


    private fun saveOutcome(idOfCategory: Int, sum: Float){
        val outcomeEvent = OutcomeEvent(idOfCategory, sum, getCurrentDateTime().toString("yyyy/MM/dd"))
        dbManager.insertToDb(outcomeEvent, DataBase.TABLE_OUTCOME_NAME)
        currentBalance -= sum
    }

    fun safeSaveOutcome(view: View){
        if(outcomeEditValue.text.isNotEmpty()) {
            saveOutcome(currentChose + 1, outcomeEditValue.text.toString().toFloat())
            outcomeEditValue.text.clear()
            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
    }

}
