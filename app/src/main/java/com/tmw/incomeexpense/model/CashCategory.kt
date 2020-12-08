package com.tmw.incomeexpense.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cash")
class CashCategory (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cash_id")
    val cash_id:Int,
    @ColumnInfo(name = "cash_name")
    val cash_name:String

) : Parcelable