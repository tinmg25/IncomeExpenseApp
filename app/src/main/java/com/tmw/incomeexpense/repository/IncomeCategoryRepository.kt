package com.tmw.incomeexpense.repository

import androidx.lifecycle.LiveData
import com.tmw.incomeexpense.dao.IncomeCategoryDao
import com.tmw.incomeexpense.model.IncomeCategory

class IncomeCategoryRepository(private val incomeCategoryDao:IncomeCategoryDao) {

    val allIncomeCategories:LiveData<List<IncomeCategory>> = incomeCategoryDao.getAllIncomeCategory()

    suspend fun insertIncomeCategory(incomeCategory: IncomeCategory){
        incomeCategoryDao.insertIncomeCategory(incomeCategory)
    }

    suspend fun deleteIncomeCategory(incomeCategory: IncomeCategory){
        incomeCategoryDao.deleteIncomeCategory(incomeCategory)
    }

    suspend fun deleteAllIncomeCategory(){
        incomeCategoryDao.deleteAllIncomeCategory()
    }

    suspend fun updateIncomeCategory(incomeCategory: IncomeCategory){
        incomeCategoryDao.updateIncomeCategory(incomeCategory)
    }
}