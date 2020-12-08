package com.tmw.incomeexpense.ui.expense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Expense
import com.tmw.incomeexpense.ui.home.ExpenseHistoryFragmentDirections
import kotlinx.android.synthetic.main.item_expense_history.view.*

class ExpenseHistoryAdapter(var listener: RowClickListener) :
    RecyclerView.Adapter<ExpenseHistoryAdapter.ExpenseViewHolder>() {

    private var expenseHistoryList = emptyList<Expense>()

    fun setOnClickListener(e_history: List<Expense>) {
        this.expenseHistoryList = e_history
    }

    class ExpenseViewHolder(itemView: View, val listener: RowClickListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(expense: Expense) {
            itemView.img_expense_history_delete.setOnClickListener {
                listener.onDeleteExpenseClick(expense)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense_history, parent, false)
        return ExpenseViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentItem = expenseHistoryList[position]
        holder.itemView.txt_expense_history_category.text = currentItem.category
        holder.itemView.txt_expense_history_date.text = currentItem.date
        holder.itemView.txt_expense_history_amount.text = currentItem.amount.toString()

        holder.itemView.expense_history.setOnClickListener {
            val action =
                ExpenseHistoryFragmentDirections.actionExpenseHistoryFragmentToUpdateExpenseFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }

        holder.bind(expenseHistoryList[position])

    }

    override fun getItemCount(): Int {
        return expenseHistoryList.size
    }

    fun setDataExpenseHistory(expenseHistory: List<Expense>) {
        this.expenseHistoryList = expenseHistory
        notifyDataSetChanged()
    }

    interface RowClickListener {
        fun onDeleteExpenseClick(expense: Expense)
    }

}