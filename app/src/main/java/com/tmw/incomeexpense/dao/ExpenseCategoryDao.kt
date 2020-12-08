package com.tmw.incomeexpense.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.incomeexpense.model.Expense
import com.tmw.incomeexpense.model.ExpenseCategory

@Dao
interface ExpenseCategoryDao {
    @Query("select * from expense_category order by exp_cname asc")
    fun getAllExpenseCategory():LiveData<List<ExpenseCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpenseCategory(expenseCategory: ExpenseCategory)

    @Delete
    suspend fun deleteExpenseCategory(expenseCategory: ExpenseCategory)

    @Query("delete from expense_category")
    suspend fun deleteAllExpenseCategory()

    @Update
    suspend fun updateExpenseCategory(expenseCategory: ExpenseCategory)
}