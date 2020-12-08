package com.tmw.incomeexpense.repository

import androidx.lifecycle.LiveData
import com.tmw.incomeexpense.dao.CashCategoryDao
import com.tmw.incomeexpense.model.CashCategory

class CashRepository(private val cashCategoryDao: CashCategoryDao){

    val allCashCategories:LiveData<List<CashCategory>> = cashCategoryDao.getAllCash()

    suspend fun insertCash(cashCategory:CashCategory){
        cashCategoryDao.insertCash(cashCategory)
    }

    suspend fun deleteCash(cashCategory: CashCategory){
        cashCategoryDao.deleteCash(cashCategory)
    }

    suspend fun deleteAllCash(){
        cashCategoryDao.deleteAllCash()
    }

    suspend fun updateCash(cashCategory: CashCategory){
        cashCategoryDao.updateCash(cashCategory)
    }

}