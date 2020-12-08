package com.tmw.incomeexpense.ui.home


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tmw.incomeexpense.database.IncExpDatabase
import com.tmw.incomeexpense.repository.IncomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val homeRepository:HomeRepository

    init {
        val incomeDao= IncExpDatabase.getDatabase(application).incomeDao()

        homeRepository=HomeRepository(incomeDao)
    }
}