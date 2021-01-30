package com.example.myproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myproject.MoneyEvent

class DbManager private constructor(context: Context) {

    companion object {
        var dbmanager: DbManager? = null
        fun getInstance(context: Context): DbManager{
            dbmanager?.let{
                return it
            }
            val instance = DbManager(context)
            dbmanager = instance
            return instance
        }
    }

    private val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = dbHelper.writableDatabase
    }

    fun closeDb(){
        dbHelper.close()
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

    fun sumForPeriod(tableName: String, dateAfter:String, dateBefore:String, category_id: Int?): Float {
        var sum =0f
        var columnNameValue = ""
        var columnNameDate = ""
        var columnNameCategoryId = ""

        when(tableName){
            DataBase.TABLE_OUTCOME_NAME -> {
                columnNameValue = DataBase.COLUMN_OUTCOME_VALUE
                columnNameDate = DataBase.COLUMN_OUTCOME_DATE
                columnNameCategoryId = DataBase.COLUMN_OUTCOME_CATEGORY_ID
            }
            DataBase.TABLE_INCOME_NAME -> {
                columnNameValue = DataBase.COLUMN_INCOME_VALUE
                columnNameDate = DataBase.COLUMN_INCOME_DATE
                columnNameCategoryId = DataBase.COLUMN_INCOME_CATEGORY_ID
            }
        }
        var sql = "SELECT SUM($columnNameValue) FROM $tableName WHERE" +
                " $columnNameDate >= '$dateAfter' AND $columnNameDate" +
                " <= '$dateBefore'"
        if (category_id != null){
            sql +=  "AND $columnNameCategoryId = $category_id"
        }
        
        val cursor = db?.rawQuery(sql, null)
        cursor?.use {
            while (it.moveToNext()) {
                sum = it.getFloat(0)
            }
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