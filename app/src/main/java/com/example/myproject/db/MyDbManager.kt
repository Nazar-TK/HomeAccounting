package com.example.myproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.myproject.MoneyEvent

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

    fun closeDb(){
        myDbHelper.close()
    }


    fun insertToDb(moneyEvent: MoneyEvent, tableName: String) {
        db?.insert(tableName, null, moneyEvent.toContentValues())
    }

    fun tableOpenInformation(tableName: String, columns:Array<String> = arrayOf("*"), groupBy:String = "${DataBase.TABLE_OUTCOME_CATEGORY_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_NAME}" ): ArrayList<ArrayList<String>> {
        var sql = ""

        when(tableName){
            DataBase.TABLE_OUTCOME_NAME -> sql = "SELECT ${columns.joinToString()} FROM ${DataBase.TABLE_OUTCOME_NAME} LEFT OUTER JOIN " +
                    "${DataBase.TABLE_OUTCOME_CATEGORY_NAME} ON ${DataBase.TABLE_OUTCOME_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_ID}=${DataBase.TABLE_OUTCOME_CATEGORY_NAME}._id " +
                    "ORDER BY $groupBy"
            DataBase.TABLE_INCOME_NAME -> sql = "SELECT ${columns.joinToString()} FROM ${DataBase.TABLE_INCOME_NAME} LEFT OUTER JOIN " +
                    "${DataBase.TABLE_INCOME_CATEGORY_NAME} ON ${DataBase.TABLE_INCOME_NAME}.${DataBase.COLUMN_INCOME_CATEGORY_ID}=${DataBase.TABLE_INCOME_CATEGORY_NAME}._id " +
                    "ORDER BY $groupBy"
        }

        val cursor = db?.rawQuery(sql, null)
        val dataList = ArrayList<ArrayList<String>>()

        cursor?.use {
            while (it.moveToNext()) {
                val tmpDataList = ArrayList<String>()
                var i = 0
                while (i < it.columnCount) {
                    tmpDataList.add(it.getString(i))
                    ++i
                }
                dataList.add(tmpDataList)
            }
        }
        return dataList
    }

    fun getSumOfColumn(tableName: String, columnName: String): Float{
        val sql = "SELECT SUM($columnName) FROM $tableName"
        val cursor = db?.rawQuery(sql, null)
        var res = 0f
        cursor?.use {
            it.moveToFirst()
            res = it.getFloat(1)
        }
        return res
    }

    fun calendarData(tableName: String, dateAfter:String, dateBefore:String, id: String ): Double  {  //ArrayList<String>

        val dataList = ArrayList<String>()
        val db = myDbHelper.readableDatabase
        val selectQuery = "SELECT "+ DataBase.COLUMN_OUTCOME_VALUE  +" FROM " + tableName + " WHERE " + DataBase.COLUMN_OUTCOME_DATE + ">='" + dateAfter + "' AND " + DataBase.COLUMN_OUTCOME_DATE + "<='" + dateBefore + "'"+ " AND " + DataBase.COLUMN_OUTCOME_CATEGORY_ID + "=" + id
        val cursor = db.rawQuery(selectQuery, null)
        while (cursor?.moveToNext()!!) {
            dataList.add(cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_OUTCOME_VALUE)).toString())
        }
        cursor.close()
        var sum =0.0
        for (item in dataList) {
            sum += item.toFloat()
        }

        return sum
    }

    //ГАВНОФУНКЦІЯ, ЗАХАРДКОДИВ ПО ПРІКОЛУ
    fun readDbData1(tableName: String, dateAfter:String, dateBefore:String ): Double  {

        val dataList = ArrayList<String>()
        val db = myDbHelper.readableDatabase
        val selectQuery = "SELECT "+ DataBase.COLUMN_OUTCOME_VALUE  +" FROM " + tableName + " WHERE " + DataBase.COLUMN_OUTCOME_DATE + ">='" + dateAfter + "' AND " + DataBase.COLUMN_OUTCOME_DATE + "<='" + dateBefore + "'"
        val cursor = db.rawQuery(selectQuery, null)
        while (cursor?.moveToNext()!!) {
            dataList.add(cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_OUTCOME_VALUE)).toString())
        }
        cursor.close()
        var sum =0.0
        for (item in dataList) {
            sum += item.toFloat()
        }

        return sum
    }

    fun readColumn(tableName: String, columnName: String): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(tableName,null, null, null, null, null, null)
        cursor?.use {
            while (it.moveToNext()) {
                dataList.add(it.getString(it.getColumnIndex(columnName)))
            }
        }
        return dataList
    }


}