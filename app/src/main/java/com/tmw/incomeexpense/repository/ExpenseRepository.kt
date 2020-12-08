package com.tmw.incomeexpense.repository

import androidx.lifecycle.LiveData
import com.tmw.incomeexpense.dao.ExpenseDao
import com.tmw.incomeexpense.model.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val allExpense: LiveData<List<Expense>> = expenseDao.getAllExpense()

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }

    suspend fun deleteAllExpense() {
        expenseDao.deleteAll()
    }

    suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    fun e_sumAmount(): LiveData<Int> {
        return expenseDao.e_sumAmount()
    }

    fun getExpenseByDate(start_date:String,end_date:String):LiveData<List<Expense>>{
        return expenseDao.getExpenseByDate(start_date,end_date)
    }

    fun getExpenseByCategory(category:String):LiveData<Int>{
        return expenseDao.getExpenseByCategory(category)
    }

    fun getExpenseByCash(cash:String):LiveData<Int>{
        return expenseDao.getExpenseByCash(cash)
    }

    fun deleteByDate(start_date: String,end_date: String){
        return expenseDao.deleteByDate(start_date,end_date)
    }
}