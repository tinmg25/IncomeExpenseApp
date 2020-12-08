package com.tmw.incomeexpense.ui.income

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.incomeexpense.database.IncExpDatabase
import com.tmw.incomeexpense.model.Income
import com.tmw.incomeexpense.repository.IncomeRepository
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application) : AndroidViewModel(application) {

    private val incomeRepository: IncomeRepository

    val allIncome: LiveData<List<Income>>

    init {
        val incomeDao = IncExpDatabase.getDatabase(application).incomeDao()

        incomeRepository = IncomeRepository(incomeDao)

        allIncome = incomeRepository.allIncome
    }

    fun insertIncome(income: Income) = viewModelScope.launch {
        incomeRepository.insertIncome(income)
    }

    fun deleteIncome(income: Income) = viewModelScope.launch {
        incomeRepository.deleteIncome(income)
    }

    fun deleteAllIncome() = viewModelScope.launch {
        incomeRepository.deleteAllIncome()
    }

    fun updateIncome(income: Income) = viewModelScope.launch {
        incomeRepository.updateIncome(income)
    }

    fun i_sumAmount(): LiveData<Int> {
        return incomeRepository.i_sumAmount()
    }

    fun getIncomeByDate(startDate: String, endDate: String): LiveData<List<Income>> {
        return incomeRepository.getIncomeByDate(startDate, endDate)
    }

    fun getIncomeByCategory(category: String): LiveData<Int> {
        return incomeRepository.getIncomeByCategory(category)
    }

    fun getIncomeByCash(cash: String): LiveData<Int> {
        return incomeRepository.getIncomeByCash(cash)
    }
}