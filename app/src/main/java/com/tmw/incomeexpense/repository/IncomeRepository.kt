package com.tmw.incomeexpense.repository

import androidx.lifecycle.LiveData
import com.tmw.incomeexpense.dao.IncomeDao
import com.tmw.incomeexpense.model.Income

class IncomeRepository(private val incomeDao: IncomeDao) {

    val allIncome: LiveData<List<Income>> = incomeDao.getAllIncome()

    suspend fun insertIncome(income: Income) {
        incomeDao.insertIncome(income)
    }

    suspend fun deleteIncome(income: Income) {
        incomeDao.deleteIncome(income)
    }

    suspend fun deleteAllIncome() {
        incomeDao.deleteAllIncome()
    }

    suspend fun updateIncome(income: Income) {
        incomeDao.updateIncome(income)
    }

    fun i_sumAmount(): LiveData<Int> {
        return incomeDao.i_sumAmount()
    }

    fun getIncomeByDate(startDate: String, endDate: String): LiveData<List<Income>> {
        return incomeDao.getIncomeByDate(startDate, endDate)
    }

    fun getIncomeByCategory(category: String): LiveData<Int> {
        return incomeDao.getIncomeByCategory(category)
    }

    fun getIncomeByCash(cash: String): LiveData<Int> {
        return incomeDao.getIncomeByCash(cash)
    }

}