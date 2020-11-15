package com.example.myproject.db

import android.provider.BaseColumns

object MyDbNameClass: BaseColumns {
    const val TABLE_NAME = "income_categories"
    const val COLUMN_NAME_CATEGORY = "category"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Home.db"
}