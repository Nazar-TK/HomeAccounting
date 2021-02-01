package com.example.myproject

import android.content.ContentValues
import androidx.core.content.contentValuesOf
import com.example.myproject.db.DataBase

abstract class MoneyEvent() {

    open fun toContentValues() : ContentValues{
        return contentValuesOf()
    }

}

class IncomeEvent(private val category_id: Int, private val value: Float, private val date: String) : MoneyEvent(){
    override fun toContentValues() : ContentValues{
        return ContentValues().apply{
            put(DataBase.COLUMN_INCOME_CATEGORY_ID, category_id)
            put(DataBase.COLUMN_INCOME_VALUE, value)
            put(DataBase.COLUMN_INCOME_DATE, date)
        }
    }
}

class OutcomeEvent(private val category_id: Int, private val value: Float, private val date: String) : MoneyEvent(){
    override fun toContentValues() : ContentValues{
        return ContentValues().apply{
            put(DataBase.COLUMN_OUTCOME_CATEGORY_ID, category_id)
            put(DataBase.COLUMN_OUTCOME_VALUE, value)
            put(DataBase.COLUMN_OUTCOME_DATE, date)
        }
    }
}