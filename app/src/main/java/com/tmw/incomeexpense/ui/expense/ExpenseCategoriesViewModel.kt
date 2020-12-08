package com.tmw.incomeexpense.ui.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.incomeexpense.database.IncExpDatabase
import com.tmw.incomeexpense.model.ExpenseCategory
import com.tmw.incomeexpense.repository.ExpenseCategoryRepository
import kotlinx.coroutines.launch

class ExpenseCategoriesViewModel (application: Application):AndroidViewModel(application){

    private val expenseCategoryRepository:ExpenseCategoryRepository

    val allExpenseCategories: LiveData<List<ExpenseCategory>>

    init {
        val expenseCategoryDao=IncExpDatabase.getDatabase(application).expenseCategoryDao()

        expenseCategoryRepository= ExpenseCategoryRepository(expenseCategoryDao)

        allExpenseCategories=expenseCategoryRepository.allExpenseCategories
    }

    fun insertExpenseCategory(expenseCategory: ExpenseCategory)=viewModelScope.launch {
        expenseCategoryRepository.insertExpenseCategory(expenseCategory)
    }

    fun deleteExpenseCategory(expenseCategory: ExpenseCategory)=viewModelScope.launch {
        expenseCategoryRepository.deleteExpenseCategory(expenseCategory)
    }

    fun deleteAllExpenseCategory()=viewModelScope.launch {
        expenseCategoryRepository.deleteAllExpenseCategory()
    }

    fun updateExpenseCategory(expenseCategory: ExpenseCategory)=viewModelScope.launch {
        expenseCategoryRepository.updateExpenseCategory(expenseCategory)
    }
}