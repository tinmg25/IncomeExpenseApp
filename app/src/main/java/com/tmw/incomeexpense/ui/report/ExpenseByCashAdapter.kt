package com.tmw.incomeexpense.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import kotlinx.android.synthetic.main.item_income_by_cash.view.*

class ExpenseByCashAdapter:RecyclerView.Adapter<ExpenseByCashAdapter.ExpenseByCashViewHolder>() {

    private var expensebyCashList= emptyList<ExpenseCashReport>()

    inner class ExpenseByCashViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bind(expense:ExpenseCashReport){
            itemView.cash_name.text=expense.cash
            itemView.cash_amount.text=expense.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseByCashViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_income_by_cash,parent,false)
        return ExpenseByCashViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseByCashViewHolder, position: Int) {
        holder.bind(expensebyCashList[position])
    }

    override fun getItemCount(): Int {
        return expensebyCashList.size
    }

    fun setDataExpense(expense:List<ExpenseCashReport>){
        this.expensebyCashList=expense
        notifyDataSetChanged()
    }

}