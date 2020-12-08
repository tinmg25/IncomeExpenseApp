package com.tmw.incomeexpense.ui.income

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.incomeexpense.database.IncExpDatabase
import com.tmw.incomeexpense.model.IncomeCategory
import com.tmw.incomeexpense.repository.IncomeCategoryRepository
import kotlinx.coroutines.launch

class IncomeCategoriesViewModel(application: Application) :AndroidViewModel(application){

    private val incomeCategoryRepository:IncomeCategoryRepository

    val allIncomeCategories: LiveData<List<IncomeCategory>>

    init{
        val incomeCategoryDao=IncExpDatabase.getDatabase(application).incomeCategoryDao()

        incomeCategoryRepository= IncomeCategoryRepository(incomeCategoryDao)

        allIncomeCategories=incomeCategoryRepository.allIncomeCategories
    }

    fun insertIncomeCategory(incomeCategory: IncomeCategory)=viewModelScope.launch {
        incomeCategoryRepository.insertIncomeCategory(incomeCategory)
    }

    fun deleteIncomeCategory(incomeCategory: IncomeCategory)=viewModelScope.launch {
        incomeCategoryRepository.deleteIncomeCategory(incomeCategory)
    }

    fun deleteAllIncomeCategory()=viewModelScope.launch {
        incomeCategoryRepository.deleteAllIncomeCategory()
    }

    fun updateIncomeCategory(incomeCategory: IncomeCategory)=viewModelScope.launch {
        incomeCategoryRepository.updateIncomeCategory(incomeCategory)
    }
}