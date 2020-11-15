package com.example.myproject.db

import android.provider.BaseColumns

object MyDbNameClass {
    const val TABLE_NAME = "income_categories"
    const val COLUMN_NAME_CATEGORY = "category"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "HomeDb.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_CATEGORY TEXT)"
    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}