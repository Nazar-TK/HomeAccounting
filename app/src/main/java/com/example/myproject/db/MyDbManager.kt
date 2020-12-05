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

    fun getCategorySum(idCat: String, columnName: String, tableName: String): Int
    {
        val categoryList = readColumn(DataBase.TABLE_OUTCOME_NAME, DataBase.COLUMN_OUTCOME_CATEGORY_ID)
        var sum = 0
        for (id in categoryList) {
            if(id == idCat)
                sum += getBySmth(idCat, columnName, tableName, DataBase.COLUMN_OUTCOME_CATEGORY_ID).toInt()
        }
        return sum
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

    fun getByID(id: String, columnName: String, tableName: String): String {

        var userInfo  =  ""
        val db = myDbHelper.readableDatabase
        val  selectQuery = "SELECT " +  columnName  + " FROM " + tableName + " WHERE " + BaseColumns._ID +" = " + id
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        userInfo = cursor.getString(cursor.getColumnIndex(columnName))

                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return userInfo
    }

    private fun getBySmth(id: String, columnName: String, tableName: String, smth:String): String {

        var userInfo  =  ""
        val db = myDbHelper.readableDatabase
        val  selectQuery = "SELECT " +  columnName  + " FROM " + tableName + " WHERE " + smth +" = " + id
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        userInfo = cursor.getString(cursor.getColumnIndex(columnName))

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

        val cursor = db?.query(tableName,null, null, null, null, null, null)
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