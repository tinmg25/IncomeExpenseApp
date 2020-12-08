package com.tmw.incomeexpense.repository

import androidx.lifecycle.LiveData
import com.tmw.incomeexpense.dao.ExpenseCategoryDao
import com.tmw.incomeexpense.model.ExpenseCategory


class ExpenseCategoryRepository (private val expenseCategoryDao: ExpenseCategoryDao){

    val allExpenseCategories:LiveData<List<ExpenseCategory>> = expenseCategoryDao.getAllExpenseCategory()

    suspend fun insertExpenseCategory(expenseCategory: ExpenseCategory){
        expenseCategoryDao.insertExpenseCategory(expenseCategory)
    }

    suspend fun deleteExpenseCategory(expenseCategory: ExpenseCategory){
        expenseCategoryDao.deleteExpenseCategory(expenseCategory)
    }

    suspend fun deleteAllExpenseCategory(){
        expenseCategoryDao.deleteAllExpenseCategory()
    }

    suspend fun updateExpenseCategory(expenseCategory: ExpenseCategory){
        expenseCategoryDao.updateExpenseCategory(expenseCategory)
    }
}