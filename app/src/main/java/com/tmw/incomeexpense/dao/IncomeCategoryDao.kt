package com.tmw.incomeexpense.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.incomeexpense.model.IncomeCategory

@Dao
interface IncomeCategoryDao {
    @Query("select * from income_category order by inc_cname asc")
    fun getAllIncomeCategory():LiveData<List<IncomeCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncomeCategory(incomeCategory: IncomeCategory)

    @Delete
    suspend fun deleteIncomeCategory(incomeCategory: IncomeCategory)

    @Query("delete from income_category")
    suspend fun deleteAllIncomeCategory()

    @Update
    suspend fun updateIncomeCategory(incomeCategory: IncomeCategory)
}