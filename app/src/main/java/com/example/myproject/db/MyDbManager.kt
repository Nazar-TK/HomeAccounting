package com.example.myproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import java.sql.Date
import java.text.SimpleDateFormat

class MyDbManager private constructor(context: Context) {

    companion object {
        var dbmanager: MyDbManager? = null
        fun getInstance(context: Context): MyDbManager{
            dbmanager?.let{
                return it
            }
            val instance = MyDbManager(context)
            dbmanager = instance
            return instance
        }
    }

    private val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }
    fun GetDate(): String
    {
        val date = "SELECT Date('now')"
        return date
    }
    fun insertToDb(data: ArrayList<String>, tableName: String) {

        val columns:Array<String> = DataBase.mapTableColumns.getValue(tableName)
        var i = 0

        val values = ContentValues().apply {
            for (column in columns) {
                put(column, data[i])
                i++
            }
        }

        db?.insert(tableName, null, values)
    }
    fun getParticularUserData(id: String): String {

        var userInfo  =  ""
        val db = myDbHelper.readableDatabase
        val  selectQuery = "SELECT " +  DataBase.COLUMN_OUTCOME_CATEGORY_NAME  + " FROM " + DataBase.TABLE_OUTCOME_CATEGORY_NAME + " WHERE " + BaseColumns._ID +" = " + id
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        userInfo = cursor.getString(cursor.getColumnIndex("outcome_category_name"))

                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return userInfo
    }

    fun readDbData(tableName: String): ArrayList<String> {

        val dataList = ArrayList<String>()
        val columns:Array<String> = DataBase.mapTableColumns.getValue(tableName)

        for (column in columns) {
            val cursor = db?.query(tableName, null, null, null, null, null, null)
            while (cursor?.moveToNext()!!) {
                dataList.add(cursor.getString(cursor.getColumnIndex(column)).toString())
            }
            cursor?.close()
        }

        return dataList
    }
    fun readColumn(tableName: String, columnName: String): ArrayList<String> {

        val dataList = ArrayList<String>()

        val cursor = db?.query(tableName,null, columnName, null, null, null, null)
        while (cursor?.moveToNext()!!) {
            dataList.add(cursor.getString(cursor.getColumnIndex(columnName)).toString())
        }
        cursor?.close()
        return dataList
    }

    fun closeDb(){
        myDbHelper.close()
    }
}