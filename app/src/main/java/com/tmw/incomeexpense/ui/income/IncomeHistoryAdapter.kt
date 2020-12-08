package com.tmw.incomeexpense.ui.income

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Income
import com.tmw.incomeexpense.ui.home.IncomeHistoryFragmentDirections
import kotlinx.android.synthetic.main.item_income_history.view.*

class IncomeHistoryAdapter(var listener: RowClickListener) :
    RecyclerView.Adapter<IncomeHistoryAdapter.IncomeHistoryViewHolder>() {

    private var incomehistoryList = emptyList<Income>()

    fun setOnClickListener(i_history: List<Income>) {
        this.incomehistoryList = i_history
    }

    inner class IncomeHistoryViewHolder(itemView: View, val listener: RowClickListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(income: Income) {
            itemView.img_income_history_delete.setOnClickListener {
                listener.onDeleteIncomeClick(income)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeHistoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_income_history, parent, false)
        return IncomeHistoryViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: IncomeHistoryViewHolder, position: Int) {
        var currentItem = incomehistoryList[position]
        holder.itemView.txt_income_history_date.text = currentItem.date
        holder.itemView.txt_income_history_category.text = currentItem.category
        holder.itemView.txt_income_history_amount.text = currentItem.amount.toString()

        holder.itemView.income_history.setOnClickListener {
            val action =
                IncomeHistoryFragmentDirections.actionIncomeHistoryFragmentToUpdateIncomeFragment2(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }

        holder.bind(incomehistoryList[position])
    }

    override fun getItemCount(): Int {
        return incomehistoryList.size
    }

    fun setDataIncomeHistory(incomeHistory: List<Income>) {
        this.incomehistoryList = incomeHistory
        notifyDataSetChanged()
    }

    interface RowClickListener {
        fun onDeleteIncomeClick(income: Income)
    }

}