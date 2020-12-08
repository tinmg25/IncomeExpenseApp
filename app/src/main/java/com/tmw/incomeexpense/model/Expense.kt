package com.tmw.incomeexpense.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "expense")
class Expense (
    @PrimaryKey(autoGenerate = true)
    val exp_id:Int,
    val date:String,
    val cash:String,
    val category: String,
    val remark:String,
    val amount:Int
) : Parcelable