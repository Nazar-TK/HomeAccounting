package com.example.myproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

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

    fun insertToDb(data: ArrayList<String>, tableName: String) {

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
            else if (tableName == DataBase.TABLE_INCOME_CATEGORY_NAME) {
                for (item in DataBase.TableIncomeCategory) {
                    put(item, data[i])
                    i++
                }
            }
            else if (tableName == DataBase.TABLE_OUTCOME_CATEGORY_NAME) {
                for (item in DataBase.TableOutcomeCategory) {
                    put(item, data[i])
                    i++
                }
            }
        }

        db?.insert(tableName, null, values)
    }

    fun readDbData(tableName: String): ArrayList<String> {

        val dataList = ArrayList<String>()

         if (tableName == DataBase.TABLE_INCOME_NAME) {
             for (item in DataBase.TableIncome) {
                 val cursor = db?.query(tableName, null, null, null,null, null, null)
                while (cursor?.moveToNext()!!) {
                    dataList.add(cursor.getString(cursor.getColumnIndex(item)).toString())
                }
                 cursor?.close()
             }
        }
        else if (tableName == DataBase.TABLE_OUTCOME_NAME) {
            for (item in DataBase.TableOutcome) {
                val cursor = db?.query(tableName, null, null, null,null, null, null)
                while (cursor?.moveToNext()!!) {
                    dataList.add(cursor.getString(cursor.getColumnIndex(item)).toString())
                }
                cursor?.close()
            }

        }


        return dataList
    }

    fun closeDb(){

        myDbHelper.close()
    }
}