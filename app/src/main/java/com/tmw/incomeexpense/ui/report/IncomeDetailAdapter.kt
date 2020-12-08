package com.tmw.incomeexpense.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Income
import kotlinx.android.synthetic.main.item_income_expense_detail.view.*

class IncomeDetailAdapter(val listener:RowClickListener) :RecyclerView.Adapter<IncomeDetailAdapter.IncomeExpenseDeatilViewHolder>(){

    private var incomeList= emptyList<Income>()

    fun setOnClickListener(i_list:List<Income>){
        this.incomeList=i_list
    }

    class IncomeExpenseDeatilViewHolder(itemView: View,var listener: RowClickListener):RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IncomeExpenseDeatilViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_income_expense_detail,parent,false)
        return IncomeExpenseDeatilViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: IncomeExpenseDeatilViewHolder, position: Int) {
        val income=incomeList[position]
        holder.itemView.txt_date.text=income.date
        holder.itemView.txt_category.text=income.category
        holder.itemView.txt_cash.text=income.cash
        holder.itemView.txt_amount.text=income.amount.toString()
        holder.itemView.txt_remark.text=income.remark
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

    fun setDataIncome(income:List<Income>){
        this.incomeList=income
        notifyDataSetChanged()
    }

    interface RowClickListener{
        fun onItemClickListener(income: Income)
    }

}