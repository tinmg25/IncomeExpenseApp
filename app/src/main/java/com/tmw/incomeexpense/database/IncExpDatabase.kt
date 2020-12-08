package com.tmw.incomeexpense.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmw.incomeexpense.dao.*
import com.tmw.incomeexpense.model.*

@Database(
    entities = arrayOf(
        Income::class,
        Expense::class,
        IncomeCategory::class,
        ExpenseCategory::class,
        CashCategory::class
    ), version = 1,exportSchema = false
)
abstract class IncExpDatabase : RoomDatabase() {

    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun incomeCategoryDao(): IncomeCategoryDao
    abstract fun expenseCategoryDao(): ExpenseCategoryDao
    abstract fun cashCategoryDao(): CashCategoryDao

    companion object {

        @Volatile
        private var INSTANCE: IncExpDatabase? = null

        fun getDatabase(context: Context): IncExpDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IncExpDatabase::class.java,
                    "incexp_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
