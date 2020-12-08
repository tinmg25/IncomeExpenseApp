package com.tmw.incomeexpense.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Expense
import kotlinx.android.synthetic.main.item_income_expense_detail.view.*

class ExpenseDetailAdapter(private var listener:RowClickListener) :RecyclerView.Adapter<ExpenseDetailAdapter.ExpenseViewHolder>(){

    private var expenseList= emptyList<Expense>()

    class ExpenseViewHolder(itemView: View,val listener:RowClickListener):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_income_expense_detail,parent,false)
        return ExpenseViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense=expenseList[position]
        holder.itemView.txt_date.text=expense.date
        holder.itemView.txt_category.text=expense.category
        holder.itemView.txt_cash.text=expense.cash
        holder.itemView.txt_amount.text=expense.amount.toString()
        holder.itemView.txt_remark.text=expense.remark

    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun setDataExpense(expense:List<Expense>){
        this.expenseList=expense
        notifyDataSetChanged()
    }

    interface RowClickListener{
        fun onItemClickListener(expense:Expense)
    }

}