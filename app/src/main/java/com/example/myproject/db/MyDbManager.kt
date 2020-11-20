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

    fun insertToDb(data: ArrayList<String>, tableName: String, columnName: String) {

        val values = ContentValues().apply {
            var i = 0
            if (tableName == DataBase.TABLE_INCOME_NAME) {
                for (item in DataBase.TableIncome) {
                    put(item, data[i])
                    i++
                }
            }
            else if (tableName == DataBase.TABLE_OUTCOME_NAME) {
                for (item in DataBase.TableOutcome) {
                    put(item, data[i])
                    i++
                }
            }
        }

        db?.insert(tableName, null, values)
    }

    fun readDbData(tableName: String, columnName: String){//: ArrayList<String> {

//        val dataList = ArrayList<String>()
//
//        val cursor = db?.query(tableName, null, null, null,null, null, null)
//            if (tableName == DataBase.TABLE_INCOME_NAME) {
//                for (item in DataBase.TableIncome) {
//                    while (cursor?.moveToNext()!!) {
//                        dataList.add(cursor.getString(cursor.getColumnIndex(item)))
//                    }
//                }
//            }
//            else if (tableName == DataBase.TABLE_OUTCOME_NAME) {
//                for (item in DataBase.TableOutcome) {
//                    while (cursor?.moveToNext()!!) {
//                        dataList.add(cursor.getString(cursor.getColumnIndex(item)))
//                    }
//                }
//            }
//
//            cursor.close()
//        return dataList
    }

    fun closeDb(){

        myDbHelper.close()
    }
}