package com.tmw.incomeexpense.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import kotlinx.android.synthetic.main.item_income_by_category.view.*

class ExpenseByCategoryAdapter :RecyclerView.Adapter<ExpenseByCategoryAdapter.ExpenseCategoryViewHolder>(){

    private var expenseByCategoryList= emptyList<ExpenseReport>()

    inner class ExpenseCategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(expense:ExpenseReport){
            itemView.category_name.text=expense.category
            itemView.category_amount.text=expense.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseCategoryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_income_by_category,parent,false)
        return ExpenseCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseCategoryViewHolder, position: Int) {
        holder.bind(expenseByCategoryList[position])
    }

    override fun getItemCount(): Int {
        return expenseByCategoryList.size
    }

    fun setDataExpenseReport(expense:List<ExpenseReport>){
        this.expenseByCategoryList=expense
        notifyDataSetChanged()
    }

}