package com.example.myproject
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object
    {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "EmployeeDatabase"
        private const val OUTCOME_CATEGORY = "EmployeeTable"

        private const val KEY_ID = "id"
        private const val KEY_FOOD = "food"
        private const val KEY_CREDIT = "credit"
        private const val KEY_TRANSPORT = "transport"
        private const val KEY_FUN = "fun"
        private const val KEY_UTILITIES = "utilities"
    }
    override fun onCreate(db: SQLiteDatabase?)
    {
        val OUTCOME_CATEGORY = ("CREATE TABLE " + OUTCOME_CATEGORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FOOD + " FOOD," + KEY_CREDIT + " CREDIT" + KEY_TRANSPORT
                + " TRANSPORT" + KEY_FUN+ " FUN" + KEY_UTILITIES+ " UTILITIES" + ")")
        db?.execSQL(OUTCOME_CATEGORY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}

