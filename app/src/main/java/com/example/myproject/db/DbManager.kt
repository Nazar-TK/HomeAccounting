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

    fun tableOpenInformation(tableName: String, columns:Array<String> = arrayOf("*"), orderBy:String = "${DataBase.TABLE_OUTCOME_CATEGORY_NAME}.${DataBase.COLUMN_OUTCOME_CATEGORY_NAME}" ): ArrayList<ArrayList<String>> {

        var otherTableName = ""
        var columnWithId = ""
                                        //41-55 making sql query
        when(tableName){
            DataBase.TABLE_OUTCOME_NAME -> {
                otherTableName = DataBase.TABLE_OUTCOME_CATEGORY_NAME
                columnWithId = DataBase.COLUMN_OUTCOME_CATEGORY_ID
            }
            DataBase.TABLE_INCOME_NAME -> {
                otherTableName = DataBase.TABLE_INCOME_CATEGORY_NAME
                columnWithId = DataBase.COLUMN_INCOME_CATEGORY_ID
            }
        }

        val sql = "SELECT ${columns.joinToString()} FROM $tableName LEFT OUTER JOIN " +
                "$otherTableName ON $tableName.$columnWithId=$otherTableName._id " +
                "ORDER BY $orderBy"

        val cursor = db?.rawQuery(sql, null)
        val dataList = ArrayList<ArrayList<String>>()

        cursor?.use {
            while (it.moveToNext()) {
                val tmpDataList = ArrayList<String>()
                for (i in 0 until it.columnCount) {
                    tmpDataList.add(it.getString(i))
                }
                dataList.add(tmpDataList)
            }
        }
        return dataList
    }

    fun sumForPeriod(tableName: String, dateAfter:String, dateBefore:String, category_id: Int?): Float {
        var sum =0f
        var columnNameValue = ""
        var columnNameDate = ""
        var columnNameCategoryId = ""
                                     //77-94 making sql query
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
            cursor.moveToFirst()
            sum = it.getFloat(0)
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