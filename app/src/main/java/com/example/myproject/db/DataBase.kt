package com.example.myproject.db

import android.provider.BaseColumns

object DataBase : BaseColumns{
    //Database version
    const val DATABASE_VERSION = 1

    //Database name
    const val DATABASE_NAME = "HomeDb"

    //Table names
    const val TABLE_INCOME_NAME = "income"
    const val TABLE_OUTCOME_NAME = "outcome"
    const val TABLE_INCOME_CATEGORY_NAME = "income_category"
    const val TABLE_OUTCOME_CATEGORY_NAME = "outcome_category"

    //Columns names
    const val COLUMN_INCOME_CATEGORY_ID = "income_category_id"
    const val COLUMN_INCOME_VALUE = "income_value"
    const val COLUMN_OUTCOME_CATEGORY_ID = "outcome_category_id"
    const val COLUMN_OUTCOME_VALUE = "outcome_value"
    const val COLUMN_INCOME_CATEGORY_NAME = "income_category_name"
    const val COLUMN_OUTCOME_CATEGORY_NAME = "outcome_category_name"


    // Table Create Statements
    const val CREATE_INCOME_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_INCOME_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_INCOME_CATEGORY_ID TEXT, $COLUMN_INCOME_VALUE TEXT)"

    const val CREATE_OUTCOME_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_OUTCOME_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_OUTCOME_CATEGORY_ID TEXT, $COLUMN_OUTCOME_VALUE TEXT)"

    const val CREATE_INCOME_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_INCOME_CATEGORY_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_INCOME_CATEGORY_NAME TEXT)"

    const val CREATE_OUTCOME_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_OUTCOME_CATEGORY_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_OUTCOME_CATEGORY_NAME TEXT)"


    //Arrays Of Columns
    val TableIncome: Array<String> = arrayOf(COLUMN_INCOME_CATEGORY_ID, COLUMN_INCOME_VALUE)
    val TableOutcome: Array<String> = arrayOf(COLUMN_OUTCOME_CATEGORY_ID, COLUMN_OUTCOME_VALUE)
    val TableIncomeCategory: Array<String> = arrayOf(COLUMN_INCOME_CATEGORY_NAME)
    val TableOutcomeCategory: Array<String> = arrayOf(COLUMN_OUTCOME_CATEGORY_NAME)

    //Table categories
    val OutcomeCategories: Array<String> = arrayOf("credits","food","entertainment","transport","utilites")


    // Table Delete Statements
    const val DELETE_TABLE_INCOME = "DROP TABLE IF EXISTS $TABLE_INCOME_NAME"
    const val DELETE_TABLE_OUTCOME = "DROP TABLE IF EXISTS $TABLE_OUTCOME_NAME"
    const val DELETE_TABLE_INCOME_CATEGORY = "DROP TABLE IF EXISTS $TABLE_INCOME_CATEGORY_NAME"
    const val DELETE_TABLE_OUTCOME_CATEGORY = "DROP TABLE IF EXISTS $TABLE_OUTCOME_CATEGORY_NAME"
}