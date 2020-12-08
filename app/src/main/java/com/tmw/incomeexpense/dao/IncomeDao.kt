package com.tmw.incomeexpense.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.incomeexpense.model.Income

@Dao
interface IncomeDao {
    @Query("select * from income order by date asc")
    fun getAllIncome(): LiveData<List<Income>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncome(income: Income)

    @Delete
    suspend fun deleteIncome(income: Income)

    @Query("delete from income")
    suspend fun deleteAllIncome()

    @Update
    suspend fun updateIncome(income: Income)

    @Query("select SUM(amount) from income")
    fun i_sumAmount(): LiveData<Int>

    @Query("Select * FROM income WHERE date BETWEEN :startDate AND :endDate")
    fun getIncomeByDate(startDate: String, endDate: String): LiveData<List<Income>>

    @Query(value = "SELECT SUM(amount) FROM income WHERE category= :category")
    fun getIncomeByCategory(category: String): LiveData<Int>

    @Query(value = "select sum(amount) from income where cash=:cash")
    fun getIncomeByCash(cash: String): LiveData<Int>

}