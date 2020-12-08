package com.tmw.incomeexpense.ui.expense

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Expense
import kotlinx.android.synthetic.main.fragment_expense.view.*
import kotlinx.android.synthetic.main.fragment_income.view.*

class ExpenseAdapter :RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>(){

    private var expenseList= emptyList<Expense>()

    class ExpenseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val exp_date=itemView.txt_expense_date
        val exp_cash=itemView.spinner_cash_expense
        val exp_category=itemView.spinner_category_expense
        val exp_remark=itemView.etd_expense_remark
        val exp_amount=itemView.etd_expense_amount

        fun bind(expense:Expense){
            exp_date.text=expense.date
            exp_cash.selectedItem
            exp_category.selectedItem
            exp_remark.setText(expense.remark)
            exp_amount.setText(expense.amount.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_expense,parent,false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenseList[position])
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

}