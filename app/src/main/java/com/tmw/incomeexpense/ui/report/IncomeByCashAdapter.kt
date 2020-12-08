package com.tmw.incomeexpense.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import kotlinx.android.synthetic.main.item_income_by_cash.view.*

class IncomeByCashAdapter : RecyclerView.Adapter<IncomeByCashAdapter.IncomeByCashViewHolder>() {

    private var incomebyCashList = emptyList<IncomeCashReport>()

    inner class IncomeByCashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(income:IncomeCashReport){
            itemView.cash_name.text=income.cash
            itemView.cash_amount.text=income.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeByCashViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_income_by_cash,parent,false)
        return IncomeByCashViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeByCashViewHolder, position: Int) {
        holder.bind(incomebyCashList[position])
    }

    override fun getItemCount(): Int {
        return incomebyCashList.size
    }

    fun setDataIncome(income:List<IncomeCashReport>){
        this.incomebyCashList=income
        notifyDataSetChanged()
    }

}