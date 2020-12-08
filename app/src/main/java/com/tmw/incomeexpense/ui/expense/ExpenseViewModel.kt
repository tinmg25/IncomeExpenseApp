package com.tmw.incomeexpense.ui.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.incomeexpense.database.IncExpDatabase
import com.tmw.incomeexpense.model.Expense
import com.tmw.incomeexpense.repository.ExpenseRepository
import kotlinx.coroutines.launch
import kotlin.math.exp

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val expenseRepository: ExpenseRepository

    val allExpense: LiveData<List<Expense>>

    init {
        val expenseDao = IncExpDatabase.getDatabase(application).expenseDao()

        expenseRepository = ExpenseRepository(expenseDao)

        allExpense = expenseRepository.allExpense
    }

    fun insertExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.insertExpense(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.deleteExpense(expense)
    }

    fun deleteAllExpense() = viewModelScope.launch {
        expenseRepository.deleteAllExpense()
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.updateExpense(expense)
    }

    fun e_sumAmount(): LiveData<Int> {
        return expenseRepository.e_sumAmount()
    }

    fun getExpenseByDate(start_date:String,end_date:String): LiveData<List<Expense>>{
        return expenseRepository.getExpenseByDate(start_date,end_date)
    }

    fun getExpenseByCategory(category:String): LiveData<Int>{
        return expenseRepository.getExpenseByCategory(category)
    }

    fun getExpenseByCash(cash:String):LiveData<Int>{
        return expenseRepository.getExpenseByCash(cash)
    }

    fun deleteByDate(start_date: String,end_date: String){
        return expenseRepository.deleteByDate(start_date,end_date)
    }
}