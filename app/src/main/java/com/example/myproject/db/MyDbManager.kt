package com.example.myproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {

    private val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(data: String, tableName: String, columnName: String) {

        val values = ContentValues().apply {

            put(columnName, data)
        }

        db?.insert(tableName, null, values)
    }

    fun readDbData(tableName: String, columnName: String): ArrayList<String> {

        val dataList = ArrayList<String>()

          val cursor = db?.query(tableName, null, null, null,
                                                           null, null, null)

            while (cursor?.moveToNext()!!) {

                val dataText = cursor.getString(cursor.getColumnIndex(columnName))
                dataList.add(dataText.toString())
            }
            cursor.close()
            return dataList
    }

    fun closeDb(){

        myDbHelper.close()
    }
}