package com.example.myproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {

    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {

        db = myDbHelper.writableDatabase
    }

    fun insertToDb(category: String) {

        val values = ContentValues().apply {

            put(MyDbNameClass.COLUMN_NAME_CATEGORY, category)
        }

        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun readDbData(): ArrayList<String> {

        val dataList = ArrayList<String>()

          val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null,
                                                           null, null, null)

            while (cursor?.moveToNext()!!) {

                val dataText = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_CATEGORY))
                dataList.add(dataText.toString())
            }
            cursor.close()
            return dataList
    }

    fun closeDb(){

        myDbHelper.close()
    }
}