package com.example.myproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DataBase.DATABASE_NAME, null, DataBase.DATABASE_VERSION)
{

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(DataBase.CREATE_INCOME_TABLE)
        db?.execSQL(DataBase.CREATE_OUTCOME_TABLE)
        db?.execSQL(DataBase.CREATE_INCOME_CATEGORY_TABLE)
        db?.execSQL(DataBase.CREATE_OUTCOME_CATEGORY_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        db?.execSQL(DataBase.DELETE_TABLE_INCOME)
        db?.execSQL(DataBase.DELETE_TABLE_OUTCOME)
        db?.execSQL(DataBase.DELETE_TABLE_INCOME_CATEGORY)
        db?.execSQL(DataBase.DELETE_TABLE_OUTCOME_CATEGORY)
        onCreate(db)
    }
}