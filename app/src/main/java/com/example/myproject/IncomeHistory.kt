package com.example.myproject

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
import kotlinx.android.synthetic.main.activity_income_history.*
import kotlinx.android.synthetic.main.activity_income_history.linearLayout
import kotlinx.android.synthetic.main.activity_outcome_history.*

class IncomeHistory : AppCompatActivity() {
    private val myDbManager = DbManager.getInstance(this)
    val ROWS = fillFields().size
    val info = fillFields()
    val COLUMNS = 3
    val tableLayout by lazy { TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_income_history)

        val lp = TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tableLayout.apply {
            layoutParams = lp
            isShrinkAllColumns = true
        }
        createTable(ROWS, COLUMNS)
    }

    fun createTable(rows: Int, cols: Int) {
        var sum = 0.0
        for (i in 0 until rows) {

            val row = TableRow(this)
            row.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

            for (j in 0 until cols) {
                val layer = TextView(this)
                layer.apply {
                    layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)
                    text = info[i][j] + " " + "\t" + "\n"
                    textSize = 19F
                }
                layer.setTextColor(Color.parseColor("black"));

                row.addView(layer)
            }
            tableLayout.addView(row)
        }
        linearLayout.addView(tableLayout)
        val incomeValueList = myDbManager.readColumn(DataBase.TABLE_INCOME_NAME, DataBase.COLUMN_INCOME_VALUE)
        for (item in incomeValueList) {
            sum += item.toFloat()
        }
        sumIncome.text = sum.toString()
    }


    private fun fillFields() : ArrayList<ArrayList<String>> {

        val columns: Array<String> = arrayOf(
            "${DataBase.TABLE_INCOME_CATEGORY_NAME}.${DataBase.COLUMN_INCOME_CATEGORY_NAME}",
            "${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_DATE}",
            "${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_VALUE}"
        )
        val groupBy =
            "${DataBase.TABLE_INCOME_CATEGORY_NAME}.${DataBase.COLUMN_INCOME_CATEGORY_NAME}"
        val info: ArrayList<ArrayList<String>> =
            myDbManager.tableOpenInformation(DataBase.TABLE_INCOME_NAME, columns, groupBy)

        return info

    }
}
