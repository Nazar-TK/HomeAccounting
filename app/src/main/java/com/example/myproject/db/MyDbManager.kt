package com.example.myproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

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
//            else if (tableName == DataBase.TABLE_INCOME_CATEGORY_NAME) {
//                for (item in DataBase.TableIncomeCategory) {
//                    put(item, data[i])
//                    i++
//                }
//            }
//            else if (tableName == DataBase.TABLE_OUTCOME_CATEGORY_NAME) {
//                for (item in DataBase.TableOutcomeCategory) {
//                    put(item, data[i])
//                    i++
//                }
//            }
        }

        db?.insert(tableName, null, values)
    }
//    fun getParticularUserData(id: String): String {
//
//        var userInfo  = ""
//        val db = myDbHelper.readableDatabase
//        //val selectQuery = "SELECT"  + "FROM " + DataBase.TABLE_OUTCOME_CATEGORY_NAME + " WHERE " + BaseColumns._ID + " = '" + id + "'"
//        //val cursor = db?.rawQuery("SELECT" +  DataBase.COLUMN_OUTCOME_CATEGORY_NAME + "FROM" + DataBase.TABLE_OUTCOME_NAME + "WHERE" + BaseColumns._ID +"==" +id
//        val  selectQuery = "SELECT " +  DataBase.COLUMN_OUTCOME_CATEGORY_NAME  + " FROM " + DataBase.TABLE_OUTCOME_CATEGORY_NAME + " WHERE " + BaseColumns._ID +" = " + id
//
//
//        val cursor = db?.rawQuery(selectQuery,null)
//
//        userInfo= cursor.toString()
//        cursor?.close()
//        return userInfo
//    }


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
        if (tableName == DataBase.TABLE_OUTCOME_CATEGORY_NAME) {
            for (item in DataBase.TableOutcomeCategory) {
                val cursor = db?.query(tableName, null, null, null,null, null, null)
                while (cursor?.moveToNext()!!) {
                    dataList.add(cursor.getString(cursor.getColumnIndex(item)).toString())
                }
                cursor?.close()
            }
        }
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
                //val cursor = db?.rawQuery("SELECT id, outcome_category_id FROM $DataBase.TABLE_OUTCOME_NAME WHERE outcome_category_id == 1")
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