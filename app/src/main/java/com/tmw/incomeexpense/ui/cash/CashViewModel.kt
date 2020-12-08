package com.tmw.incomeexpense.ui.cash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.incomeexpense.database.IncExpDatabase
import com.tmw.incomeexpense.model.CashCategory
import com.tmw.incomeexpense.repository.CashRepository
import kotlinx.coroutines.launch

class CashViewModel (application: Application):AndroidViewModel(application){

    private val cashRepository:CashRepository

    val allCashCategories:LiveData<List<CashCategory>>


    init{
        val cashDao=IncExpDatabase.getDatabase(application).cashCategoryDao()

        cashRepository= CashRepository(cashDao)

        allCashCategories=cashRepository.allCashCategories
    }

    fun insertCash(cashCategory: CashCategory)=viewModelScope.launch {
        cashRepository.insertCash(cashCategory)
    }

    fun deleteCash(cashCategory: CashCategory)=viewModelScope.launch {
        cashRepository.deleteCash(cashCategory)
    }

    fun deleteAllCash()=viewModelScope.launch {
        cashRepository.deleteAllCash()
    }

    fun updateCash(cashCategory: CashCategory)=viewModelScope.launch {
        cashRepository.updateCash(cashCategory)
    }

}