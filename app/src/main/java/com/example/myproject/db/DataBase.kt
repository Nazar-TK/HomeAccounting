package com.example.myproject.db

import android.provider.BaseColumns

object DataBase {
    const val TABLE_INCOME_NAME = "income"
    const val TABLE_INCOME_CATEGORIES_NAME = "income categories"
    const val TABLE_OUTCOME_NAME = "outcome"
    const val TABLE_OUTCOME_CATEGORIES = "outcome categories"

    const val COLUMN_INCOME_CATEGORY = "income category"

    const val COLUMN_INCOME_NAME_ID = "income name id"
    const val COLUMN_INCOME_VALUE = "income value"

    const val COLUMN_OUTCOME_CATEGORY = "outcome category"

    const val COLUMN_OUTCOME_NAME_ID = "outcome name id"
    const val COLUMN_OUTCOME_VALUE = "outcome value"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "HomeDb.db"

    const val CREATE_INCOME_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_INCOME_NAME (" +
            "${BaseColumns._ID} INTEGER, $COLUMN_INCOME_NAME_ID INTEGER, $COLUMN_INCOME_VALUE INTEGER)"

    const val CREATE_INCOME_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_INCOME_CATEGORIES_NAME (" +
            "${BaseColumns._ID} INTEGER, $COLUMN_INCOME_CATEGORY TEXT)"

    const val CREATE_OUTCOME_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_OUTCOME_NAME (" +
            "${BaseColumns._ID} INTEGER, $COLUMN_OUTCOME_NAME_ID INTEGER, $COLUMN_OUTCOME_VALUE INTEGER)"

    const val CREATE_OUTCOME_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_OUTCOME_CATEGORIES (" +
            "${BaseColumns._ID} INTEGER, $COLUMN_OUTCOME_CATEGORY TEXT)"

    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_INCOME_CATEGORIES_NAME"
}