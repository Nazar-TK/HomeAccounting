package com.example.myproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DataBase.DATABASE_NAME, null, DataBase.DATABASE_VERSION)
{

    override fun onCreate(db: SQLiteDatabase?)
    {
        db?.execSQL(DataBase.CREATE_INCOME_CATEGORIES_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        db?.execSQL(DataBase.DELETE_TABLE)
        onCreate(db)
    }
}