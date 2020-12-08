package com.tmw.incomeexpense.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "income")
class Income(
    @PrimaryKey(autoGenerate = true)
    val income_id:Int,
    val date:String,
    val cash:String,
    val category: String,
    val remark:String,
    val amount:Int
):Parcelable