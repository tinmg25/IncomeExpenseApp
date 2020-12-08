package com.tmw.incomeexpense.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "income_category")
class IncomeCategory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "inc_cid")
    val inc_cid: Int,
    @ColumnInfo(name = "inc_cname")
    val inc_cname: String
) : Parcelable