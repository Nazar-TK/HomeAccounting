package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.db.DataBase
import com.example.myproject.db.DbManager
import com.example.myproject.SettingsActivity
import kotlinx.android.synthetic.main.activity_outcome_history.*
import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class OutcomeHistory : AppCompatActivity() {
    private val myDbManager = DbManager.getInstance(this)
    val ROWS = fillFields().size
    val info = fillFields()
    val COLUMNS = 3
    val tableLayout by lazy { TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_outcome_history)

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
            row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

            for (j in 0 until cols) {
                val layer = TextView(this)
                layer.apply {
                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)
                    text = "  " + info[i][j] + " " + "\t" + "\n"
                    textSize = 20F
                }


                layer.setTextColor(Color.parseColor("white"));



                row.addView(layer)
            }
            tableLayout.addView(row)
        }
        linearLayout.addView(tableLayout)
        val dataList =  myDbManager.readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_VALUE)
        for (item in dataList) {
            sum += item.toFloat()
        }
        sumOutcome.text = sum.toString()
    }

    private fun fillFields() :ArrayList<ArrayList<String>> {

        val columns: Array<String> = arrayOf(
            "${DataBase.TABLE_OUTCOME_CATEGORY_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_NAME}",
            "${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_DATE}",
            "${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_VALUE}"
        )
        val groupBy =
            "${DataBase.TABLE_OUTCOME_CATEGORY_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_NAME}"
        val info: ArrayList<ArrayList<String>> =
            myDbManager.tableOpenInformation(DataBase.TABLE_OUTCOME_NAME, columns, groupBy)

       return info
    }
}
