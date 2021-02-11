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
import kotlinx.android.synthetic.main.activity_income.*
import java.text.SimpleDateFormat
import java.util.*

class IncomeActivity : AppCompatActivity() {

    private var currentChose = 0
    private val dbManager = DbManager.getInstance(this)
    var stringsInSpinner = arrayListOf<String>()

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

        tempbutton2.setOnClickListener{
            callDialog()
        }
        updateSpinner()

        incomeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentChose = position
                if (currentChose == stringsInSpinner.size - 1) {
                    callDialog()
                    updateSpinner()
                }
            }
        }
    }



    private fun callDialog(){
        val promptsView = LayoutInflater.from(this).inflate(R.layout.prompt, null);
        val mDialogBuilder = AlertDialog.Builder(this);
        val userInput = promptsView.findViewById<EditText>(R.id.input_text);

        mDialogBuilder.setView(promptsView);

        mDialogBuilder
            .setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                dbManager.insertToDb(IncomeCategoryEvent(userInput.text.toString()), DataBase.TABLE_INCOME_CATEGORY_NAME)
            }
            .setNegativeButton("Назад"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            }

        val alertDialog = mDialogBuilder.create();
        alertDialog.show();

    }

    private fun updateSpinner(){
        stringsInSpinner = dbManager.readColumn(DataBase.TABLE_INCOME_CATEGORY_NAME, DataBase.COLUMN_INCOME_CATEGORY_NAME).apply {add("+ Додати категорію")}
        incomeSpinner.adapter = object : ArrayAdapter<String> (this, R.layout.style_spinner, stringsInSpinner){
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

    private fun saveIncome(category_id: Int, sum: Float) {
        val myDbManager = DbManager.getInstance(this)
        val incomeEvent = IncomeEvent(category_id, sum, getCurrentDateTime().toString("yyyy/MM/dd"))
        myDbManager.insertToDb(incomeEvent, DataBase.TABLE_INCOME_NAME)
        currentBalance += sum
    }

    fun safeSaveIncome(view: View){
        if(incomeEditValue.text.isNotEmpty()) {
            saveIncome(currentChose + 1, incomeEditValue.text.toString().toFloat())
            incomeEditValue.text.clear()
            Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
    }
}