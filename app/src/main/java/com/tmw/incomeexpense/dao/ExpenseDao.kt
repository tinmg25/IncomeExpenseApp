package com.tmw.incomeexpense.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.incomeexpense.model.Expense

@Dao
interface ExpenseDao {
    @Query("select * from expense order by date asc")
    fun getAllExpense(): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("delete from expense")
    suspend fun deleteAll()

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("select SUM(amount) from expense")
    fun e_sumAmount(): LiveData<Int>

    @Query("select * from expense where date between :start_date and :end_date")
    fun getExpenseByDate(start_date:String,end_date:String): LiveData<List<Expense>>

    @Query("select sum(amount) from expense where category=:category")
    fun getExpenseByCategory(category:String): LiveData<Int>

    @Query("select sum(amount) from expense where cash=:cash")
    fun getExpenseByCash(cash:String): LiveData<Int>

    @Query("delete from expense where date between :start_date and :end_date")
    fun deleteByDate(start_date: String,end_date: String)
}