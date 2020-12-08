package com.tmw.incomeexpense.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.incomeexpense.model.CashCategory

@Dao
interface CashCategoryDao {
    @Query("select * from cash")
    fun getAllCash():LiveData<List<CashCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCash(cashCategory: CashCategory)

    @Delete
    suspend fun deleteCash(cashCategory: CashCategory)

    @Query("delete from cash")
    suspend fun deleteAllCash()

    @Update
    suspend fun updateCash(cashCategory: CashCategory)
}