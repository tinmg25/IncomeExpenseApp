package com.tmw.incomeexpense.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "expense_category")
class ExpenseCategory (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="exp_cid")
    val exp_cid:Int,
    @ColumnInfo(name="exp_cname")
    val exp_cname:String
):Parcelable